RonSwansons Trusted Build Test
==================================
 
TESTED WITH: qemu arm

As documented here 
(http://adit.io/posts/2013-05-11-The-Dining-Philosophers-Problem-With-Ron-Swanson.html) 
the dining philosophers problem is one of partitioning resources to concurrent
processes such that all processes eventually get access to enough resources.
In this example the processes are duplicate (5) Ron Swansons (the character 
from Parks and Recreation) and they are sitting at a table with 5 forks. To
begin eating, a Ron Swanson must have two forks. All Ron Swansons will be 
passive threads and there will be one active thread that polls the Ron Swansons
and distributes forks. The active thread will avoid deadlock by utilizing a
resource hierarchy approach.