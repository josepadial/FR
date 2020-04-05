package codigo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
public class YodafyServidorIterativo {

	public static void main(String[] args) {
	
		// Puerto de escucha
		int port=8989;
		// array de bytes auxiliar para recibir o enviar datos.
		byte []buffer=new byte[256];
		// Número de bytes leídos
		int bytesLeidos=0;
		Socket socketConexion = null;
                DatagramSocket socketClienteUDP = null;
                DatagramPacket paquete = null;
                int puerto = 888;
                //ServerSocket socketServidor = null;
                
		try {
			// Abrimos el socket en modo pasivo, escuchando el en puerto indicado por "port"
			//////////////////////////////////////////////////
			// ...serverSocket=... (completar)
			//////////////////////////////////////////////////
			//socketServidor = new ServerSocket(port);
                        socketClienteUDP = new DatagramSocket(puerto);
			// Mientras ... siempre!
			do {
				
				// Aceptamos una nueva conexión con accept()
				/////////////////////////////////////////////////
				// socketServicio=... (completar)
				//////////////////////////////////////////////////
				try{
                                    //socketConexion = socketServidor.accept();
                                    paquete = new DatagramPacket(buffer, buffer.length);
                                    socketClienteUDP.receive(paquete);
                                    socketClienteUDP.close();
                                }catch(IOException e){
                                    System.out.println("Error: No se pudo aceptar la conexion facilitada\n");
                                }
				// Creamos un objeto de la clase ProcesadorYodafy, pasándole como 
				// argumento el nuevo socket, para que realice el procesamiento
				// Este esquema permite que se puedan usar hebras más fácilmente.
				ProcesadorYodafy procesador=new ProcesadorYodafy(socketConexion);
				procesador.procesa();
				
			} while (true);
			
		} catch (IOException e) {
			System.err.println("Error al escuchar en el puerto "+port);
		}

	}

}
