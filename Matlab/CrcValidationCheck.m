function CrcOK=CrcValidationCheck(RxBuffer)               % Checking and Verifying if crc is Ok or not Ok according to each Syncword's occurance.
    CheckSum = uint16(0); 
    CRC_INIT=0xFFFF;                                      % Hexa Value according to CRC scheme of cc1350 Texas Instrument
    CheckSum = CRC_INIT;
    PacketIndex=0;
    i = 1;
    while i <= numel(RxBuffer)
        CheckSum = calcCRC(RxBuffer(i), CheckSum);        % Calculating CRC for each Syncword's occurance , i.e for each packet.
        i = i + 1;
    end
    PacketIndex = PacketIndex + 1;                        % Current Packet Number .
    if (CheckSum == 0)
        disp(PacketIndex);
        CrcOK = true;                                     % your packet number PacketIndex is Valid.
    else
        disp(PacketIndex);
        CrcOK = false;                                    % your packet number PacketIndex is Valid.
    end    
end
