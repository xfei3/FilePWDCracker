# Description
Password cracker for Java keystore, PDF and ZIP file.
For Java keystore, JCEKS is tested, other format like JKS should be supported as well, just change the file type in the argument.

# Examples

1. Crack keystore password:
java -jar FilePWDCracker.jar path/to/TestKeystore JCEKS 6 6 25 NO-KEYPASS NO-KEYPASS 48 57

2. Crack key password in keystore:
java -jar FilePWDCracker.jar path/to/TestKeystore JCEKS 6 6 25 888888 CERT1 48 57

3. Crack zip file password:
java -jar FilePWDCracker.jar path/to/TestKeystore.zip ZIP 6 6 25 NO-KEYPASS NO-KEYPASS 48 57

4. Crack pdf file password:
java -jar FilePWDCracker.jar path/to/pdfsample.pdf PDF 6 6 50 NO-KEYPASS NO-KEYPASS 48 57

5. Crack file password with char exclusion:
java -jar FilePWDCracker.jar path/to/pdfsample.pdf PDF 6 6 50 NO-KEYPASS NO-KEYPASS 46 58 47-48,55-56

# Arguments

Sorry I used an ugly way to process arguments.

Argument 1: file path

Argument 2: file type, default is jceks

Argument 3: password min length

Argument 4: password max length

Argument 5: number of threads

Argument 6: keystore password, only use for cracking key password in keystore as you need to provide keystore password first. For other functions, just use NO-KEYPASS

Argument 7: key alias, only use for cracking key password in keystore as you need to provide key alias first. For other functions, just use NO-KEYPASS

Argument 8: Starting ASCII, default is 32, see algorithm details below

Argument 9: Ending ASCII, default is 126, see algorithm details below

Argument 10: char exclusion. For example, say the password contains chars from 46 to 58, and then you can exclude chars like java -jar .... 47-48,55-56. If you just want to exclude single char, then use something like 47-47. see algorithm details below.

# Algorithm
Permutation generation is based on answer in https://stackoverflow.com/questions/16848918/how-to-generate-the-password-with-permutation-of-string .
You can also refer to https://www.asciitable.com/ as well.
For example, if the password just cotains numbers, then use ASCII 48 - 57; if a-z, then 97-122; if all characters, then 32-126.

