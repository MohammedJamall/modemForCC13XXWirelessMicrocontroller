% @ TruncateSyncWordResultCheck function: this function returns crcCheck of all substrings occurances stored at each row of the matrix.  

function TruncateSyncWordResultCheck= TruncateSyncWordResultCheck(input1,substring) 
DSSS = 8;                                                                               % DSSS in my case is 8 that's configured by SmartRF studio application.
numberOfPayloadByte=32;                                                                 % Payload length configured in SmartRF studio as 32Bytes.
N = numberOfPayloadByte*8*2*DSSS;                                                       % Packet's format rule of payload according to Texas Instrument handbook
positions = strfind(input1(1:end-N+1), substring) + 64;                                 % First position of each SyncWord's occurance in my input signal.
TruncatedSubstring= cell2mat(arrayfun(@(idx) input1(idx+length(substring):idx+length(substring)+N-1), positions, 'uniform', 0 ).');  % this is a matrix of all my following dataoffset of syncWord occurances, at each row of this matrix represents all the following dataOffset of each occurance respectively.
[NumberOfRows,~]=size(TruncatedSubstring);                                              % NumberOfRows = number of rows
for i=1:NumberOfRows
    substring = TruncatedSubstring(i,:);                                                % Sniffing and extracting all following dataoffset at each row of TruncatedSubstring's matrix.
    ResultByteConversion(i,:)=ByteConvertRxBuffer(substring);                           % Converting each dataoffset from binary to Byte array values
    TruncateSyncWordResultCheck(i,:)=CrcValidationCheck(ResultByteConversion(i,:));     % TruncateSyncwordResult is a matrix of all Syncword occurance , each row represent respectively each syncword occurance.
end
