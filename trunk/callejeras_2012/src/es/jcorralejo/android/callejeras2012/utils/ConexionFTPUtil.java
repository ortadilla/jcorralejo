package es.jcorralejo.android.callejeras2012.utils;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPReply;

public class ConexionFTPUtil extends FTPClient {

	public void subirFichero(Integer idTransmision, byte[] fichero, String usuario, String password, String directorio) throws FTPConnectionClosedException, IOException {
		if(isConnected())
			subirFichero(new ByteArrayInputStream(fichero), idTransmision.toString());
	}

	private boolean conectarYLogar (String host, String usuario, String password) throws SocketException, IOException {
		boolean conectado = false;
		connect(host);
		int reply = getReplyCode();
		if (FTPReply.isPositiveCompletion(reply))
			conectado = login(usuario, password);
		if (!conectado)
			desconectar();
		return conectado;
	}

	public void desconectar() throws IOException {
		if(isConnected())
			disconnect();
	}

	private boolean cambiarDirectorio(String directorio) throws IOException {
		return changeWorkingDirectory(directorio);
	}

	private boolean subirFichero (InputStream in, String nombreFichero) throws IOException, FTPConnectionClosedException {
		boolean result = storeFile(nombreFichero, in);
		in.close();
		return result;
	}

	public void abrirConexionFTP(String urlFTP, String usuario, String password, String directorio) throws SocketException, IOException{
		if(conectarYLogar(urlFTP, usuario, password) && cambiarDirectorio(directorio))
			enterLocalPassiveMode();
	}

	public byte[] descargarFichero2(String nombreFichero, String host, String usuario, String password, String directorio) throws SocketException, FTPConnectionClosedException, IOException {
		byte[] fichero = null;
		if(conectarYLogar(host, usuario, password) && cambiarDirectorio(directorio)){
			enterLocalPassiveMode();
			fichero = descargarFichero(nombreFichero);
		}
		desconectar();
		return fichero;
	}

	public byte[] descargarFichero(String nombreFichero, String host, String usuario, String password, String directorio) throws FTPConnectionClosedException, IOException {
		byte[] fichero = null;
		if(isConnected())
			fichero = descargarFichero(nombreFichero);
		return fichero;
	}

	private byte[] descargarFichero (String nombreFichero) throws IOException, FTPConnectionClosedException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		retrieveFile(nombreFichero, out);
		out.close();
		return out.size()>0?out.toByteArray():null;
	}



}
