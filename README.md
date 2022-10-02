# FilePWDCracker
Password cracker for Java keystore, PDF and ZIP file.
For Java keystore, tested JCEKS, should support jks and other format as well, just change the file type.

Examples:

Crack keystore password:
java -jar FilePWDCracker.jar path/to/TestKeystore JCEKS 6 6 25 NO-KEYPASS NO-KEYPASS 48 57

Crack key password in keystore:
java -jar FilePWDCracker.jar path/to/TestKeystore JCEKS 6 6 25 888888 CERT1 48 57

Crack zip file password:
java -jar FilePWDCracker.jar path/to/TestKeystore.zip ZIP 6 6 25 NO-KEYPASS NO-KEYPASS 48 57

Crack pdf file password:
java -jar FilePWDCracker.jar path/to/pdfsample.pdf PDF 6 6 50 NO-KEYPASS NO-KEYPASS 48 57

Crack file password with char exclusion:
java -jar FilePWDCracker.jar path/to/pdfsample.pdf PDF 6 6 50 NO-KEYPASS NO-KEYPASS 46 58 47-48,55-56


Sorry I used an ugly way to process arguments.

Argument 1: file path

Argument 2: file type, default is jceks

Argument 3: password min length

Argument 4: password max length

Argument 5: number of threads

Argument 6: keystore password, only use for cracking key password in keystore as you need to provide keystore password first. For other functions, just use NO-KEYPASS

Argument 7: key alias, only use for cracking key password in keystore as you need to provide key alias first. For other functions, just use NO-KEYPASS

Argument 8: Starting ASCII, default is 32, see details below

Argument 9: Ending ASCII, default is 126, see details below

Argument 10: char exclusion. For example, say the password contains char 46 to 58, and you can exclude some chars like 47-48,55-56. If you just want to exclude single char, then just use something like 47-47


Permutation generation is based on answer in https://stackoverflow.com/questions/16848918/how-to-generate-the-password-with-permutation-of-string .
You can also refer to https://www.asciitable.com/ as well.
For example, if the password just cotains numbers, then use ASCII 48 - 57; if a-z, then 97-122; if all characters, then 32-126.

