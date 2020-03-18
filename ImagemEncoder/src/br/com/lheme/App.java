package br.com.lheme;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class App {

	public static String encodingToString(BufferedImage imagem, String tipo) {
		String imagemEmString = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		try {
			ImageIO.write(imagem, tipo, bos);
			byte[] imagemBytes = bos.toByteArray();
			
			Encoder encoder = Base64.getEncoder();
			imagemEmString = encoder.encodeToString(imagemBytes);
			
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imagemEmString;
	}
	
	public static BufferedImage decodeToImage(String imagemString) {
		BufferedImage imagem = null;
		byte[] imagemByte;
		try {
			Decoder decoder = Base64.getDecoder();
			imagemByte = decoder.decode(imagemString);
			ByteArrayInputStream bis = new ByteArrayInputStream(imagemByte);
			imagem = ImageIO.read(bis);
			bis.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return imagem;
	}
	
	public static void main(String[] args) throws MalformedURLException {
		URL url = new URL("http://raposeiras.com.br/wp-content/uploads/2011/05/DSC_15891.jpg");
		try {
			BufferedImage img = ImageIO.read(url);
			BufferedImage imagem;
			String stringImagem;
			stringImagem = encodingToString(img, "jpg");
			System.out.println(stringImagem);
			imagem = decodeToImage(stringImagem);
			
			JFrame frame = new JFrame();
			frame.setSize(400, 400);
			JLabel label = new JLabel(new ImageIcon(imagem));
			frame.add(label);
			frame.setVisible(true);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
