#!/bin/sh

# Check that exactly 3 values were passed in
if [ $# -ne 3 ]; then
  echo 'This script replaces xml element’s value with the one provided as a command parameter \n\n\tUsage: $0 <xml filename> <element name> <new value>'
  exit 127
fi
 
echo "DEBUG: Starting... [Ok]"
echo "DEBUG: searching $1 for tagname <$2> and replacing its value with '$3'"
 
# Creating a temporary file for sed to write the changes to
temp_file="repl.temp"
 
# Extracting the value from the <$2> element
value=`grep -m 1 "<$2>.*<.$2>" $1 | sed -e "s/^.*<$2/<$2/" | cut -f2 -d">"| cut -f1 -d"<"`
 
echo "DEBUG: Found the current value for the element <$2> - '$value'"
 
# Replacing elemen’s value with $3
sed -e "s/<$2>$value<\/$2>/<$2>$3<\/$2>/g" $1 > $temp_file
 
# Writing our changes back to the original file ($1)
chmod 666 $1
mv $temp_file $1