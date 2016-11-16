import java.io.*;

/**
 * Created by kjh on 16. 11. 16.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        FileInputStream input = new FileInputStream("src/data10.txt");
        BufferedReader read_table = new BufferedReader(new FileReader("src/data10_table.txt"));
        FileInputStream encoded_input = new FileInputStream("src/data10_encoded.txt");
        FileWriter table_output = new FileWriter("");
        FileWriter encode_output = new FileWriter("");
        FileWriter decode_output = new FileWriter("");

        String word = getWord(input);
        String encoded_word = getWord(encoded_input);


        // encode

        Huffman huffman = new Huffman();

        StringBuffer[] huffmanCode = huffman.table();

        table(table_output, huffmanCode);

        encode_output.write(huffman.encode(word));


        // decode

        huffman = new Huffman();
        StringBuffer temp = new StringBuffer();

        string(read_table, temp);

        huffman.decodeTree(temp);

        decode_output.write(huffman.decode(encoded_word));


        // end

        input.close();
        table_output.close();
        encode_output.close();
        read_table.close();
        decode_output.close();
    }

    private static void string(BufferedReader read_table, StringBuffer temp) throws IOException {
        while(true) {
            String code = read_table.readLine();
            if (code==null)
                break;
            String[] alpha_code = code.split(",");
            for(int i=0 ; i<alpha_code.length ; i++)
                temp.append(alpha_code[i]);
        }
    }

    private static String getWord(FileInputStream input) throws IOException {
        String word = "";
        int data;
        while((data = input.read()) != -1){
            word += (char)data;
        }
        return word;
    }

    private static void table(FileWriter table_output, StringBuffer[] huffmanCode) throws IOException {
        String write_data;
        for(int i=0 ; i<huffmanCode.length ; i++){
            if(huffmanCode[i] != null) {
                if (i == 0)
                    write_data = " , " + huffmanCode[0] + "\r\n";
                else
                    write_data = (char) (i + 96) + ", " + huffmanCode[i] + "\r\n";

                table_output.write(write_data);
            }
        }
    }
}
