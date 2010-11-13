/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.guaraba.modelo;

/**
 *
 * @author marcelo
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * @author Marcelo
 */
public class Arquivo {
    RandomAccessFile aFile;
    private ByteBuffer buf;
    private FileChannel inChannel;
    private int bytesRead;
    private int linhaAtual=0;
    private int tamanhoLinha;
    private int totalLinhasArquivo;



    public Arquivo(String nome, int tamanhoLinha) throws FileNotFoundException, IOException {
        this.tamanhoLinha = tamanhoLinha;
        aFile = new RandomAccessFile(nome, "r");
        inChannel = aFile.getChannel();
        buf = ByteBuffer.allocate(this.tamanhoLinha);
        bytesRead = inChannel.read(buf);
        totalLinhasArquivo = ((int) inChannel.size()) / tamanhoLinha ;
    }


    public String getLinha() throws IOException {
        buf.flip();
        StringBuilder s = new StringBuilder();
        while (buf.hasRemaining()) {
            s.append((char) buf.get());
        }
        s.deleteCharAt(s.length() - 1);
        return s.toString();
    }

    public String getLinha(int linha) throws IOException {
        buf.clear(); //make buffer ready for writing
        bytesRead = inChannel.read(buf, linha * 161);
        buf.flip();
        StringBuilder s = new StringBuilder();
        while (buf.hasRemaining()) {
            s.append((char) buf.get());
        }
        s.deleteCharAt(s.length() - 1);
        return s.toString();
    }

    public int Proximo() throws IOException{
        buf.clear(); //make buffer ready for writing
        return bytesRead = inChannel.read(buf);
    }

    public void Finalizar() throws IOException{
        aFile.close();
    }

    public int getTotalLinhasArquivo() {
        return totalLinhasArquivo;
    }

}
