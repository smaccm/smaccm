# 08/04/2016 Mike Whalen
# This is a script to run trusted build example models through RAMSES, CAmkES, and QEMU.

# -*- coding: utf8 -*-
from __future__ import unicode_literals
import unittest, re, sys, subprocess, os, shutil, fnmatch, time, signal

from fuzzywuzzy import fuzz
from fuzzywuzzy import process
from fuzzywuzzy import utils
from fuzzywuzzy.string_processing import StringProcessor

if sys.version_info[0] == 3:
    unicode = str

if sys.version_info[:2] == (2, 6):
    # Monkeypatch to make tests work on 2.6
    def assertLess(first, second, msg=None):
        assert first > second
    unittest.TestCase.assertLess = assertLess

#
# Configuration
#
DEFAULT_SIMILARITY = 50
DEFAULT_ALGORITHM = 'ratio'
DEFAULT_TIMEOUT = 10
OUTPUT_DIR = '/home/mike/smaccmcopter-ph2-build/camkes/test_output'
CAMKES_DIR = '/home/mike/smaccmcopter-ph2-build/camkes'
RAMSES_DIR = '/home/mike/smaccmcopter-ph2-build/ramses-demo'
RAMSES_JAR = RAMSES_DIR + '/ramses.jar'
EXPERIMENT_DIR = CAMKES_DIR + '/apps'
CONSTRUCT_OUTPUT_IF_MISSING = True

# 
# set up environment for RAMSES
#


RAMSES_RESOURCE_DIR = os.path.join(RAMSES_DIR, 'ramses_resource')
AADL2RTOS_CONFIG_DIR = os.path.join(RAMSES_DIR, 'aadl2rtos_resource')

#
# ensure paths are properly configured.
#

def checkPathExit(file_path):
  if not os.path.exists(file_path):
    print("'" + file_path + "' location does not exist")
    sys.exit(-1)

def checkPathWarn(file_path):
  if not os.path.exists(file_path):
    print("'" + file_path + "' location does not exist")
    return False;
  return True;

def checkNoPathExit(file_path):
  if os.path.exists(file_path):
    print("'" + file_path + "' location exists.  Please delete or rename it prior to run.")
    sys.exit(-1)

checkPathExit(CAMKES_DIR)
checkPathExit(EXPERIMENT_DIR)
checkNoPathExit(OUTPUT_DIR)
checkPathExit(RAMSES_DIR)
checkPathExit(RAMSES_RESOURCE_DIR)
checkPathExit(AADL2RTOS_CONFIG_DIR)
checkPathExit(RAMSES_JAR)

os.environ['RAMSES_DIR'] = RAMSES_RESOURCE_DIR 
os.environ['AADL2RTOS_CONFIG_DIR'] = AADL2RTOS_CONFIG_DIR 

#print("OS environment: {}".format(os.environ))

#
# Gather test specifications
#

os.chdir(EXPERIMENT_DIR)
aadl_files = []
for root, dirnames, filenames in os.walk(EXPERIMENT_DIR, followlinks=True):
    for filename in fnmatch.filter(filenames, 'test*.aadl'):
        aadl_files.append(os.path.join(root, filename))
if len(aadl_files) == 0:
    print("No AADL files found in '" + EXPERIMENT_DIR + "' directory tree")
    sys.exit(-1)

# 
# Run a single model through RAMSES.
# 
def run_ramses(file_path):
    (file_dir, file_name) = os.path.split(file_path)
    (file_base, ext) = os.path.splitext(file_name)
    output_file_name = os.path.join(OUTPUT_DIR, file_base + ".test_log.txt")
    args = ['java', '-jar', RAMSES_JAR, 
            '-g', 'rtos',
            '-i', '.,' + AADL2RTOS_CONFIG_DIR, 
            '-o', file_dir, 
            '-l', 'trace',
            '-s', 'test.camkes',
            '-m', 'SMACCM_SYS.aadl,'+file_name] 
    with open(output_file_name, "a") as debug:
        os.chdir(file_dir)
        sys.stdout.write("R")
        sys.stdout.flush()
        debug.write("In directory: {}\n".format(file_dir))
        debug.write("Running RAMSES with arguments: {}\n".format(args))
        proc = subprocess.Popen(args, stdout=debug, stderr=debug, env=os.environ)
        proc.wait()
        debug.write("\n")
        if (proc.returncode != 0):
           print("RAMSES process failed.  See " + output_file_name + " for more information")
           return False
        shutil.copy(os.path.join(file_dir, "make_template", "Makefile"), file_dir)
        shutil.copy(os.path.join(file_dir, "make_template", "Kconfig"), file_dir)
        shutil.copy(os.path.join(file_dir, "make_template", "Kbuild"), file_dir)
    return True

def run_camkes(file_path):
    (file_dir, file_name) = os.path.split(file_path)
    (file_base, ext) = os.path.splitext(file_name)
    output_file_name = os.path.join(OUTPUT_DIR, file_base + ".test_log.txt")
    config_file_name = os.path.join(file_dir, "testing", "camkes", ".config")
    kconfig_file_name = os.path.join(file_dir, "testing", "camkes", "Kconfig")
    if (not checkPathWarn(config_file_name) or not checkPathWarn(kconfig_file_name)):
       return False
    sys.stdout.write("C")
    sys.stdout.flush()
    shutil.copy(config_file_name, CAMKES_DIR)
    shutil.copy(kconfig_file_name, CAMKES_DIR)
    args = ['make']
    with open(output_file_name, "a") as debug:
        os.chdir(CAMKES_DIR)			
        debug.write("In directory: {}\n".format(CAMKES_DIR))
        debug.write("Running CAmkES make with arguments: {}\n".format(args))
        proc = subprocess.Popen(args, stdout=debug, stderr=debug, env=os.environ)
        proc.wait()
        debug.write("\n")
        if (proc.returncode != 0):
           print("CAmkES make process failed.  See " + output_file_name + " for more information")
           return False
    return True

def compare_output(baseline, current):
    similarity = 50;
    if (DEFAULT_ALGORITHM == 'ratio'):
       similarity = fuzz.ratio(baseline, current)
    elif (DEFAULT_ALGORITHM == 'partial_ratio'):
       similarity = fuzz.partial_ratio(baseline, current)
    elif (DEFAULT_ALGORITHM == 'token_sort_ratio'):
       similarity = fuzz.token_sort_ratio(baseline, current)
    elif (DEFAULT_ALGORITHM == 'partial_token_sort_ratio'):
       similarity = fuzz.partial_token_sort_ratio(baseline, current)
    elif (DEFAULT_ALGORITHM == 'token_set_ratio'):
       similarity = fuzz.token_set_ratio(baseline, current)
    else:
       print("Unknown similarity measure " + DEFAULT_ALGORITHM + ". Aborting")
       sys.exit(-1)
    return similarity


def run_qemu_and_compare_output(file_path):
    (file_dir, file_name) = os.path.split(file_path)
    (file_base, ext) = os.path.splitext(file_name)
    debug_file_name = os.path.join(OUTPUT_DIR, file_base + ".test_log.txt")
    output_file_name = os.path.join(OUTPUT_DIR, file_base + "_output.txt")
    baseline_file_name = os.path.join(file_dir, "testing", "camkes", "baseline_output.txt")
    args = ['qemu-system-arm', '-M', 'kzm', '-nographic', '-kernel',
            'images/capdl-loader-experimental-image-arm-imx31']
    sys.stdout.write("R ")
    sys.stdout.flush()
    with open(debug_file_name, "a") as debug:
      with open(output_file_name, "w") as output:
        os.chdir(CAMKES_DIR)
        debug.write("In directory: {}\n".format(CAMKES_DIR))
        debug.write("Running shell with arguments: {}\n".format(args))
        proc = subprocess.Popen(args, stdout=output, stderr=debug, env=os.environ)
        time.sleep(DEFAULT_TIMEOUT)
        child_pid = proc.pid
        os.kill(child_pid, signal.SIGTERM)
        if (not checkPathWarn(baseline_file_name)): 
            if (CONSTRUCT_OUTPUT_IF_MISSING):
                sys.stdout.write("<Create Baseline>")
                sys.stdout.flush()
                shutil.copyfile(output_file_name, baseline_file_name);
                return True
            else: 
                return False
        else: 
            sys.stdout.write("C")
            sys.stdout.flush()
            with open(baseline_file_name, 'r') as myfile:
                baseline_data=myfile.read().replace('\n', '')
            with open(output_file_name, 'r') as myfile:
                output_data=myfile.read().replace('\n', '')
            similarity = compare_output(baseline_data, output_data)
            if (similarity >= DEFAULT_SIMILARITY):
                debug.write("Test passes!  Similarity is: {}\n".format(similarity))
                return True
            else:
                debug.write("Test fails!  Similarity is: {}\n".format(similarity))
                debug.write("******************************baseline data******************\n.{}".format(baseline_data))
                debug.write("\n")
                debug.write("******************************current output data******************\n.{}".format(output_data))
                return False

total_tests = len(aadl_files)
passing_tests = 0

def run_all_tests():
    os.mkdir(OUTPUT_DIR)
    passing_tests = 0;
    print("Trusted build test script.  To see more information about results, see log files in {}".format(OUTPUT_DIR));
    for i, aadl_file in enumerate(aadl_files):
        sys.stdout.write("({} of {}) {} [".format(i+1, total_tests, aadl_file))
        sys.stdout.flush()
        if (run_ramses(aadl_file) and
            run_camkes(aadl_file) and
            run_qemu_and_compare_output(aadl_file)):
           print "PASSED"
           passing_tests = passing_tests + 1        
        else:
           print "FAILED"
        sys.stdout.write("]\n")
        sys.stdout.flush()
    sys.stdout.write("{} of {} tests passed.  See directory {} for information related to each test.\n".format(passing_tests, total_tests, OUTPUT_DIR))



if __name__ == '__main__':
    run_all_tests()         # run all tests
