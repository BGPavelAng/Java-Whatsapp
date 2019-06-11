import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.event.MenuEvent;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.MenuListener;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JList;
import java.awt.Dimension;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.DefaultListModel;
import javax.swing.BoxLayout;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.Security;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.sql.*;
import javax.swing.ImageIcon;

public class WhatSpy{
    
       //La parte decrypt de la base de datos msgstore.db no se publicará, el objetivo de este código es puro aprendizaje
        
        InputStream Sqldb = new FileInputStream("msgstore.db");
        
        byte[] SqlD = new byte[6];
        Sqldb.read(SqlD);
        byte[] MS = new byte[6];
        System.arraycopy(SqlD, 0, MS, 0, 6);
        Sqldb.close();
        
        if(!new String(MS, 0, MS.length, "ASCII").toLowerCase().equals("sqlite")){
            
          new File("msgstore,db").delete();
          System.out.println("fallo");
        }else{
            
            System.out.println("listo");
                 
        }
    }

    
    
    public static void main(String [] args) throws Exception{
        
        JFrame Wfram = new JFrame();
        Wfram.setTitle("WhatSpy");
        Wfram.setSize(500,600);
        Wfram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ImageIcon ico = new ImageIcon("icon-whatsapp.png");
        
        
        Wfram.setIconImage(ico.getImage());
        
        JLabel lb4 = new JLabel();
        JLabel lb5 = new JLabel("ID para actualizar");
        
        JComboBox<String>cmid = new JComboBox<>();
        
        JButton botn = new JButton("Busqueda");
        
        JMenuBar mbar = new JMenuBar();
        JComboBox <String>cmbx = new JComboBox<>();
        
        JPanel psup = new JPanel();
        JPanel pinf = new JPanel();
        psup.setPreferredSize(new Dimension(800, 600));
        
        psup.setLayout(new FlowLayout());
        
        DefaultTableModel model = new DefaultTableModel();
        JTable tabla = new JTable (model);
        JScrollPane scrollpane = new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        pinf.setLayout(new FlowLayout());
        
        JMenu menu = new JMenu("Archivo");
        menu.setMnemonic(KeyEvent.VK_A);
        mbar.add(menu);
        
        JMenu menu2 = new JMenu("Desencriptar");
        menu2.setMnemonic(KeyEvent.VK_D);
        mbar.add(menu2);
        
        JMenu menu3 = new JMenu("Avanzado");
        menu3.setMnemonic(KeyEvent.VK_A);
        mbar.add(menu3);
        
        JMenu menu4 = new JMenu("Hijacking");
        menu4.setMnemonic(KeyEvent.VK_H);
        mbar.add(menu4);
        
        
        
        menu2.addMenuListener(new MenuListener (){
            public void menuSelected(MenuEvent e){
                
            JFrame frm2 = new JFrame();
            frm2.setTitle("Desencriptar");
            JPanel psuperior = new JPanel();
            JPanel pinferior = new JPanel();
            JPanel pacp = new JPanel();
            psuperior.setLayout(new FlowLayout());
            pinferior.setLayout(new FlowLayout());
            pacp.setLayout(new FlowLayout());
            
            frm2.setLayout(new BoxLayout(frm2.getContentPane(),BoxLayout.Y_AXIS));
            
            JLabel lb1 = new JLabel("Ruta de la key");
            JButton jb1 = new JButton("key");
            
            JLabel lb2 = new JLabel("Ruta crypter12");
            JButton jb2 = new JButton("crypter12");
            
            JButton jb3 = new JButton("Aceptar");
            
            psuperior.add(lb1);
            psuperior.add(jb1);
            
            pinferior.add(lb2);
            pinferior.add(jb2);
            
            pacp.add(jb3);
            
            frm2.add(psuperior);
            frm2.add(pinferior);
            frm2.add(pacp);
            frm2.pack();
            
            frm2.setVisible(true);
            frm2.setLocationRelativeTo(null);
            
            jb1.addActionListener(new ActionListener(){
                
                public void actionPerformed(ActionEvent e){
                    
                    String fselect;
                    JFileChooser fchoose = new JFileChooser();
                    fchoose.setCurrentDirectory(new File(System.getProperty("user.home")));
                    int tresul = fchoose.showOpenDialog(null);
                    
                    if(tresul == JFileChooser.APPROVE_OPTION){
                        fselect = fchoose.getSelectedFile().getAbsolutePath();
                        lb1.setText(fselect);
                        frm2.setSize(new Dimension(600, 200));
                    }
                }
                
            });
            
            jb2.addActionListener(new ActionListener(){
                
                public void actionPerformed(ActionEvent e){
                    
                    String fsel;
                    JFileChooser fchoos = new JFileChooser();
                    fchoos.setCurrentDirectory(new File(System.getProperty("user.home")));
                    int tresu = fchoos.showOpenDialog(null);
                    
                    if(tresu == JFileChooser.APPROVE_OPTION){
                        fsel = fchoos.getSelectedFile().getAbsolutePath();
                        lb2.setText(fsel);
                        frm2.setSize(new Dimension(700, 200));
                    }
                }
                
            });
            
             jb3.addActionListener(new ActionListener(){
                
                public void actionPerformed(ActionEvent e){
                    
                   try{
                    
                    decrypt(lb1.getText(), lb2.getText());
                    
                    } catch(Exception ex){
                       ex.printStackTrace();            // Always must return something
                      }
                }
                
            });
            
            
            }
             
            
            public void menuDeselected(MenuEvent e) {}
            public void menuCanceled(MenuEvent e) {}
        });
        
        
        
        JMenuItem mItem = new JMenuItem("Abrir...");
        menu.add(mItem);
        
        mItem.addActionListener(new ActionListener(){
            
                public void actionPerformed(ActionEvent ev){
                    
                String fl;
                Connection inx = null;
                JFileChooser fchoose = new JFileChooser();
                fchoose.setCurrentDirectory(new File(System.getProperty("user.home")));
                int tresul = fchoose.showOpenDialog(null);
                
                if(tresul == JFileChooser.APPROVE_OPTION){
                        fl = fchoose.getSelectedFile().getAbsolutePath();
                        lb4.setText(fl);
                        try{
            //jar de sqlite
            inx = DriverManager.getConnection("jdbc:sqlite:" + fl);
            
            //DatabaseMetaData md = inx.getMetaData();
            //ResultSet rs = md.getTables(null,null,"%",null);
            //catálogo, esquema, patrón, array(string)
            Statement s = inx.createStatement();
            ResultSet rs = s.executeQuery("select * from sqlite_master WHERE type='table'");
            
            while(rs.next()){
                //TABLE_NAME String => table name 
                cmbx.addItem(rs.getString(3));
            }
            
            
        }catch (Exception e){
            //verificar error
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
                }
                }
        });
        
         botn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                
                Connection inx = null;
                String sI = (String)cmbx.getSelectedItem();
                model.getDataVector().removeAllElements();
                model.fireTableDataChanged();
                model.setColumnCount(0);
               try{
            //jar de sqlite
            Class.forName("org.sqlite.JDBC");
            inx = DriverManager.getConnection("jdbc:sqlite:" + lb4.getText());
            
            //DatabaseMetaData md = inx.getMetaData();
            //ResultSet rs = md.getTables(null,null,"%",null);
            //catálogo, esquema, patrón, array(string)
            Statement s = inx.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM " + sI);
            if(rs != null){
                int colm = rs.getMetaData().getColumnCount();
 
                for(int i = 1; i <= colm; i++){
                    model.addColumn(rs.getMetaData().getColumnName(i));
                }
                while(rs.next()){
                    Object []objetos = new Object[colm];
                    for(int j = 1; j <= colm; j++){
                        objetos[j-1] = rs.getObject(j);
                    }
                    model.addRow(objetos);
                }
            }
            
        }catch (Exception e){
            //verificar error
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        
                }
            }
        });
        
        menu.addSeparator();
        
        JMenuItem mItem2 = new JMenuItem("Salir");
        menu.add(mItem2);
        
        mItem2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                System.exit(0);
            }
        });
        
        JMenuItem mItem5 = new JMenuItem("Contactos");
        menu3.add(mItem5);
        
        menu3.addSeparator();
        
        mItem5.addActionListener(new ActionListener(){
            
                public void actionPerformed(ActionEvent ev){
                    
                Connection inx = null;
                        try{
            //jar de sqlite
            Class.forName("org.sqlite.JDBC");
            inx = DriverManager.getConnection("jdbc:sqlite:" + lb4.getText());
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            model.setColumnCount(0);
            
            //DatabaseMetaData md = inx.getMetaData();
            //ResultSet rs = md.getTables(null,null,"%",null);
            //catálogo, esquema, patrón, array(string)
            Statement s = inx.createStatement();
            ResultSet rs = s.executeQuery("SELECT chat_list.key_remote_jid, chat_list.key_remote_jid, chat_list.subject, chat_list.creation, max(messages.timestamp) FROM chat_list LEFT OUTER JOIN messages on messages.key_remote_jid = chat_list.key_remote_jid GROUP BY chat_list.key_remote_jid, chat_list.subject, chat_list.creation ORDER BY max(messages.timestamp) desc");
            
            
            if(rs != null){
                int colm = rs.getMetaData().getColumnCount();
 
                for(int i = 1; i <= colm; i++){
                    model.addColumn(rs.getMetaData().getColumnName(i));
                }
                while(rs.next()){
                    cmid.addItem(rs.getString(1));
                    Object []objetos = new Object[2];
                    for(int j = 1; j <= 1; j++){
                        objetos[j-1] = rs.getObject(j);
                }
                   model.addRow(objetos);
                }
            }
            
            
        }catch (Exception e){
            //verificar error
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
                
                }
        });
        
        JMenuItem mItem6 = new JMenuItem("Chat");
        menu3.add(mItem6);
        
        mItem6.addActionListener(new ActionListener(){
            
                public void actionPerformed(ActionEvent ev){
                    
                Connection inx = null;
                        try{
            //jar de sqlite
            Class.forName("org.sqlite.JDBC");
            inx = DriverManager.getConnection("jdbc:sqlite:" + lb4.getText());

            
            //DatabaseMetaData md = inx.getMetaData();
            //ResultSet rs = md.getTables(null,null,"%",null);
            //catálogo, esquema, patrón, array(string)
            
            int col = 0;
            int row = tabla.getSelectedRow();
            
            String value = tabla.getModel().getValueAt(row,col).toString();
            
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            model.setColumnCount(0);
            
            Statement s = inx.createStatement();
            ResultSet rs = s.executeQuery("SELECT _id, key_remote_jid, key_from_me, status, data, datetime(timestamp/1000, 'unixepoch', 'localtime'), media_url, media_mime_type, media_wa_type, media_size, media_name, media_duration, latitude, longitude, thumb_image, remote_resource, raw_data FROM messages WHERE  key_remote_jid = " + "'" + value + "'" + " ORDER BY timestamp asc");
            
            
            if(rs != null){
                int colm = rs.getMetaData().getColumnCount();
 
                for(int i = 1; i <= colm; i++){
                    model.addColumn(rs.getMetaData().getColumnName(i));
                }
                while(rs.next()){
                    cmid.addItem(rs.getString(1));
                    Object []objetos = new Object[colm];
                    for(int j = 1; j <= colm; j++){
                        objetos[j-1] = rs.getObject(j);
                    }
                    model.addRow(objetos);
                }
                
                
            }
            
            
        }catch (Exception e){
            //verificar error
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
                
                }
        });
        
        JMenuItem mItem7 = new JMenuItem("Eliminar mensaje");
        menu4.add(mItem7);
        
         menu4.addSeparator();
        
        mItem7.addActionListener(new ActionListener(){
            
                public void actionPerformed(ActionEvent ev){
                    
                Connection inx = null;
                
                        try{
            //jar de sqlite
            inx = DriverManager.getConnection("jdbc:sqlite:" + lb4.getText());
            System.out.println(lb4.getText());
             inx.setAutoCommit(false);
            //DatabaseMetaData md = inx.getMetaData();
            //ResultSet rs = md.getTables(null,null,"%",null);
            //catálogo, esquema, patrón, array(string)
            
            int col = 0;
            int row = tabla.getSelectedRow();
            
            String value = tabla.getModel().getValueAt(row,col).toString();

            model.fireTableDataChanged();
            
            Statement s = inx.createStatement();
            s.executeUpdate("DELETE FROM messages WHERE _id = " + value);
             inx.commit();
            
                while(row != -1){
                    
                    int mRow = tabla.convertRowIndexToModel(row);
                    model.removeRow(mRow);
                    row = tabla.getSelectedRow();
                }
                
            
        }catch (SQLException e){
            //verificar error
            
            System.exit(0);
        }
                }
                
        });
        
        
        JMenuItem mItem8 = new JMenuItem("Actualizar datos");
        menu4.add(mItem8);
        
        mItem8.addActionListener(new ActionListener(){
            
                public void actionPerformed(ActionEvent ev){
                    
                Connection inx = null;
                
                try{
          
            inx = DriverManager.getConnection("jdbc:sqlite:" + lb4.getText());

             inx.setAutoCommit(false);
             
             int colm = 0;
            
            int col = tabla.getSelectedColumn();
            String CNa = tabla.getModel().getColumnName(col);
            int row = tabla.getSelectedRow();
            
            String value = tabla.getModel().getValueAt(row,col).toString();

            model.fireTableDataChanged();
            String dI = (String)cmid.getSelectedItem();
            
            String ifstr = tabla.getModel().getColumnName(0);
            //String fstr = tabla.getModel().getColumnName(0);
            
            
            if(ifstr.equals("_id")){
            
            Statement s = inx.createStatement();
            s.executeUpdate("UPDATE messages SET " + "'" + CNa + "'" + " = " + "'" + value + "'" + " WHERE _id = " + dI + ";");
            inx.commit();
            }
            /*if(fstr.equals("key_remote_jid")){
                
            Statement s = inx.createStatement();
            s.executeUpdate("UPDATE chatlist.key_remote_jid SET " + "'" + CNa + "'" + " = " + "'" + value + "'" + " WHERE key_remote_jid = " + "'" + dI + "'" + ";");
            inx.commit();
            }*/
            
            
        }catch (SQLException e){
            //verificar error
            
            System.exit(0);
        }
                }
                
        });
        
        
        Wfram.setJMenuBar(mbar);
        psup.add(botn);
        psup.add(cmbx);
        psup.add(scrollpane);
        psup.add(lb5);
        psup.add(cmid);
        Wfram.add(psup);

        Wfram.setVisible(true);
        Wfram.setLocationRelativeTo(null);
        
        
    }
}