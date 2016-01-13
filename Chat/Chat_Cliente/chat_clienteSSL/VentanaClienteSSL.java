package chat_clienteSSL;
import java.io.*;
import java.net.*;
import javax.net.ssl.*;
import javax.swing.*;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author David Algás
 */
public class VentanaClienteSSL extends javax.swing.JFrame 
{
    private static final long serialVersionUID= 1L;
    static SSLSocket socket = null;
    
    //Streams 
    static DataInputStream fentrada; //Para leer los Mensajes de los Usuarios
    DataOutputStream fsalida; //Para escribir el mesaje
    
    //Variables
    String nombre;
    static boolean repetir =true;
    static String texto = "";
    
    //Diseño
    static JList listaDirec = new JList();
    static FTPClient cliente = new FTPClient();
    String servidor = "127.0.0.1";
    String user = "usuario";
    String pasw= "clave";
    boolean login;
    
    public VentanaClienteSSL(SSLSocket s,String nombre) 
    {
        super("Ventana Cliente SSL: " + nombre);
        setIconImage(new ImageIcon(getClass().getResource("../imagenes/Icon_Chat.png")).getImage());
        initComponents();
        setLayout(null);
        setResizable(false);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        socket = s;
        this.nombre = nombre;
        
        try
        {
            fentrada = new DataInputStream(socket.getInputStream());
            fsalida = new DataOutputStream(socket.getOutputStream());
            String texto = "> Entra en el Chat ..." + nombre;
            fsalida.writeUTF(texto); //Escribe mensaje de entrada
        }
        catch(ConnectException ex)
        {}
        catch(IOException e)
        {
            System.out.println("ERROR E/S");
            System.exit(0);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textarea_chat = new javax.swing.JTextArea();
        mensaje = new javax.swing.JTextField();
        btnEnviar = new javax.swing.JButton();
        btnDesconectar = new javax.swing.JButton();
        btnFichero = new javax.swing.JButton();
        lbl_fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textarea_chat.setEditable(false);
        textarea_chat.setColumns(20);
        textarea_chat.setRows(5);
        jScrollPane1.setViewportView(textarea_chat);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, 341, 310));
        getContentPane().add(mensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 12, 341, -1));

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 100, -1));

        btnDesconectar.setText("Desconectar");
        btnDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesconectarActionPerformed(evt);
            }
        });
        getContentPane().add(btnDesconectar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, 100, -1));

        btnFichero.setText("Subir fichero");
        btnFichero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFicheroActionPerformed(evt);
            }
        });
        getContentPane().add(btnFichero, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 100, -1));

        lbl_fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondoCLiente.jpg"))); // NOI18N
        getContentPane().add(lbl_fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 380));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFicheroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFicheroActionPerformed
        SubirFichero sf = new SubirFichero();
        System.out.println("Conexion finalizada");
        
       
    }//GEN-LAST:event_btnFicheroActionPerformed

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed

        String texto = nombre + ">" + mensaje.getText();
        try
        {
            mensaje.setText(""); //Limpio area de mensajes
            fsalida.writeUTF(texto);
        }
        catch(IOException e1)
        {
            System.out.println("Error SSL btnEnviar: "+e1);
        }
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void btnDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesconectarActionPerformed

        String texto = " > Abandona el Chat ..." + nombre;
        try
        {
            fsalida.writeUTF(texto);
            fsalida.writeUTF("*");
            repetir = false; //para salir del bucle
        }
        catch(IOException e1)
        {
            System.out.println("Error SSL btnDesconectar: "+e1);
        }
        System.exit(0);
    }//GEN-LAST:event_btnDesconectarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaClienteSSL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaClienteSSL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaClienteSSL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaClienteSSL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        String ip = "";
        String puerto = "";
        String nombre = "";
        
        int ax = JOptionPane.showConfirmDialog(null, "¿Conectarse en Local?");
        if(ax == JOptionPane.YES_OPTION) 
        {
            nombre = JOptionPane.showInputDialog("Introduce tu nombre o nick: ");
            ip = "localhost";
            puerto = Integer.toString(2015);
        }
        else if(ax == JOptionPane.NO_OPTION)
        {
            nombre = JOptionPane.showInputDialog("Introduce tu Nick: ");
            ip = JOptionPane.showInputDialog("Direccion Ip: ");
            puerto = JOptionPane.showInputDialog("Puerto: ");
        }
        
        System.setProperty("javax.net.ssl.trustStore", "UsuarioAlmacenSSL");
	System.setProperty("javax.net.ssl.trustStorePassword", "890123");
	SSLSocketFactory sfact = (SSLSocketFactory) SSLSocketFactory.getDefault();
	
        SSLSocket s = null;
        
        try
        {
            //cliente y servidor se ejecutan en la maquina local
            s =(SSLSocket) sfact.createSocket(ip, Integer.parseInt(puerto));
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "IMPOSIBLE CONECTAR CON EL SERVIDOR\n" + e.getMessage(),
                    "<<MENSAJE DE ERROR:1>>", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        if(!nombre.trim().equals(""))
        {
            VentanaClienteSSL cliente = new VentanaClienteSSL(s, nombre);
            cliente.setBounds(0, 0, 490, 395);
            cliente.setVisible(true);
            
            HiloClienteSSL hilo = new HiloClienteSSL();
            hilo.start();
        }
        else
        {
            System.out.println("El nombre esta vacio...");
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDesconectar;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnFichero;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_fondo;
    private javax.swing.JTextField mensaje;
    public static javax.swing.JTextArea textarea_chat;
    // End of variables declaration//GEN-END:variables
}