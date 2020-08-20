function ByteConvertRxBuffer=ByteConvertRxBuffer(rx)           % Converting binary array stream to Byte array- each 8bit to unsigned int value.
    rx=num2str(rx);
    rx=rx(rx~=' ');
    rx=bin2dec(reshape(rx,8,[])')';
    ByteConvertRxBuffer=uint8([rx,zeros(1,512-length(rx))]);   % array of Byte values according to given input. N/8=512
end



