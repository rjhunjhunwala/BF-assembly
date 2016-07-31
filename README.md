# BF-assembly
<hr/>
An assembler to convet human readable psuedocode into [brain****](https://esolangs.org/wiki/Brainfuck) a language with only 8 instructions
Allows easy coding of this esolang through simple commands.
Currently functional commands are
<ol>
<li>printLine which prints a line</li>
<li>print String which can contain spaces which prints "String which can contain spaces" the string literals NEWLINE and SPACE are also escape sequences to print new lines and spaces respectively</li>
<li>printSpace prints a space</li>
<li>set someInteger someOtherInteger there is 51 hardcoded heap spots you can set any of them to whatever number you wish</li>
<li>ld loads stdin and puts the length of stdin in spot 51</li>
<li>cat (works like the unix command) and echos stdin</li>
<li>for heapSpot string leaves heapsopt unchanged and outputs the string heapSpot times</li>
<li>More commands will be added. They are all case sensitive</li>
<li>RawBF ++++ adds some raw bf code to your project (not recommended)</li>
</ol>
