function set_up_project()
%set_up_project  Configure the environment for this project
%
%   Set up the environment for the current project. This function is set to
%   Run at Startup.

%   Copyright 2011-2014 The MathWorks, Inc.

% Use Simulink Project API to get the current project:
project = simulinkproject;
projectRoot = project.RootFolder;

% Set the location of slprj to be the "work" folder of the current project:
myCacheFolder = fullfile(projectRoot, 'work');
if ~exist(myCacheFolder, 'dir')
    mkdir(myCacheFolder)
end
Simulink.fileGenControl('set', 'CacheFolder', myCacheFolder, ...
   'CodeGenFolder', myCacheFolder);

% Set working folder to the project folder:
cd(projectRoot);

end

