function y = loadFile(filename)         % Reads IQ raw Data Samples from the rtlsdr file.
fid = fopen(filename,'rb');
y = fread(fid,'uint8=>double');         % Reading the file as uint8 double 
y = y-127;
y = y(1:2:end) + i*y(2:2:end);
