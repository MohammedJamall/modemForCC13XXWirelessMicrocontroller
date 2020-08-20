
import java.util.*;

public class GlobalMembers
{
	public static Integer valueOf(final String substring)
	{
		char retChar = '\0';
		Integer total = 0;
		int counter = 1;
		for (int i = DefineConstants.byteSize; i > 0; i--)
		{
			if (substring.charAt(i - 1) == '1')
			{
				total += counter;
			}
			if (substring.charAt(i - 1) != ' ')
			{
				counter *= 2;
			}
		}
		return total;
	}

	public static void deBin(final String rx)
	{
		Integer[] ByteConvertRxBuffer = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		byte[] rxBuffer = new byte[512];
		int ireal = 0; // notice that last two last bytes are for CRC , this means last two index here are CRC value for CRC0 , CRC1 .
		short checksum; // CRC of 2byte i.e 16bits.
		boolean crcOK = false;
		byte i;
		checksum = (short) DefineConstants.CRC_INIT;
		System.out.print("{ ");
		for (int i1 = 0; i1 < rx.length(); i1 += DefineConstants.byteSize)
		{
			String nibble = new String(new char[DefineConstants.byteSize]);
			for (int j = 0; j < DefineConstants.byteSize; j++)
			{
				nibble = tangible.StringFunctions.changeCharacter(nibble, j, rx.charAt(i1 + j));
			}
			System.out.print(valueOf(nibble));
			ByteConvertRxBuffer[ireal++] = valueOf(nibble);
			if (i1 + DefineConstants.byteSize != rx.length())
			{
				System.out.print(", ");
			}
		}
		System.out.print("}");
		System.out.print("\n");
		for (int i1 = 0; i1 < ByteConvertRxBuffer.length; i1++)
		{
			rxBuffer[i1] = ByteConvertRxBuffer[i1].byteValue();

		}
		for (i = 0; i < rxBuffer.length ; i++)
		{
			checksum = calcCRC(rxBuffer[i], checksum);

		}
		PacketIndex++;
		if (checksum == 0)
		{
			crcOK = true;
			System.out.print("  your packet number ");
			System.out.print(PacketIndex);
			System.out.print(" :");
			System.out.print("Valid");
			System.out.print("\n");

		}
		else
		{
			crcOK = false;
			System.out.print("your packet number ");
			System.out.print(PacketIndex);
			System.out.print(" :");
			System.out.print("inValid");
			System.out.print("\n");

		}
	}

	public static short calcCRC(byte crcData, short crcReg)
	{
		byte i;
		for (i = 0; i < 8; i++)
		{
			if ((((crcReg & 0x8000) >> 8) ^ (crcData & 0x80)) != 0)
			{
				crcReg = (short)((crcReg << 1) ^ DefineConstants.CRC16_POLY);
			}
			else
			{
				crcReg = (short)(crcReg << 1);
			}
			crcData <<= 1;
		}
		return crcReg;
	}

	public static int PacketIndex = 0;
	public static ArrayList<tangible.Pair<Integer, String>> findPattern(final String str, final String pattern)
	{
		ArrayList<tangible.Pair<Integer, String>> vec = new ArrayList<tangible.Pair<Integer, String>>();
		tangible.Pair<Integer, String> m = new tangible.Pair<Integer, String>();


		int index;
		int pos = 0;
                int DSSS = 4;
                int numberOfPayloadByte=32;
                int N=0;
                N = numberOfPayloadByte*8*2*DSSS ;
                while ((index = str.indexOf(pattern, pos)) != -1)
                {
                    m.first = index; //start of Syncword
                    index += pattern.length();
                    m.second = str.substring(index, N); // Extracting payload data
                    vec.add(m);
                    pos = index;
                }

		return new ArrayList<tangible.Pair<Integer, String>>(vec);
	}

	public static void main(String[] args) 
	{
		String PacketData = "Here paste your Decrypted binary Data Packet(matlab output) -output of variable DecryptedBinarySignal in matlab";
		String SyncWord = "1111110111101011001010011000000000000010000101001101011001111111"; // syncword pattern
		int notfound = PacketData.indexOf(SyncWord);
		assert notfound != -1; //if not found as exit and don't complete.
		ArrayList<tangible.Pair<Integer, String>> matches = findPattern(PacketData, SyncWord);
		for (tangible.Pair<Integer, String> match : matches)
		{
			System.out.print("Match found at position: ");
			System.out.print(match.first);
			System.out.print("\n");
			System.out.print("String is ");
			System.out.print(match.second);
			System.out.print("\n");
			deBin(match.second);
		}

	}
}

