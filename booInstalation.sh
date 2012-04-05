cp /Users/renaudjenny/Library/Mail\ Downloads/Librairies/librxtxSerial.jnilib /Library/Java/Extensions/

sudo dscl . -append /groups/_uucp GroupMembership username 
sudo chgrp uucp /var/lock 
sudo chmod 775 /var/lock
