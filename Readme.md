Enigma workshop, see https://github.com/niwhede/enigma-workshop
This is my implementation of the Enigma machine in Java.
For description of the machine refer to Wikipedia


data/
Contains cyphers for all rotors and reflectors



Can be customised with the 5 different settings:

-Ring order - the order of the 3 rotors

-Initial position - the starting position of the rotors

-Ring setting - the positioning of the wiring compared to the rotors themselves

-Reflector - the reflector used to feed the output of the last rotor as an input of the same rotor

-Plugboard Setting - the optional extra letter substitutions.


To be provided as input:

# I:1 II:1 III:1 - QA ED FG BO LP CS RT UJ HN ZW

THEQUICKBROWNFOXJUMPSOVERTHELAZYDOG

VAUFLPVWMQIVFWNPCGPGVPIMKUWZREEDTTQ

I:1 II:2 III:10

Where the first line contains the rotors, their starting position and the plugboard settings (if any). The second line is the input text. The third line is the encrypted text. The last line is the rotors and their position after the message was entered.



enigma
Run ./enigma to encode a message. Example:


./enigma encode "THEQUICKBROWNFOXJUMPSOVERTHELAZYDOG" -r "III:1-1 II:1-1 I:1-1" -p "QA ED FG BO LP CS RT UJ HN ZW" -v


where


./enigma encode "<String to encrypt>" -r "<rotor I's type>:<starting position>-<Ring Setting> <rotor II's type>:<starting position>-<Ring Setting> <rotor III type>:<starting position>-<Ring Setting>" -p "<The plugboard pairs>" -v
