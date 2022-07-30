
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author 91859
 */
public class viewalloc extends javax.swing.JFrame {
 Connection conn=null;
PreparedStatement stmt=null;
ResultSet rs=null;
Statement st=null;

    public int ar[][];
    String room[];
    public int b;
    int[] bench=new int[12];
    /**
     * Creates new form viewalloc
     */
    public viewalloc() {
        initComponents();
        printTable();
    }
    public  viewalloc(int a[][],String rooms[],int bench){
        ar=a;
        room=rooms;
        b=bench;
        table(b);
       
    }
    public void deleteArrangement(){
        
            try {
                String url="jdbc:mysql://localhost:3306/project";
            String uname="root";
            String password="archana@2002";
            Connection conn = DriverManager.getConnection(url,uname,password);
                Statement st = conn.createStatement();
                String sql="DELETE FROM arrangement";
               
                st.execute(sql);
            } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
            
    }
    public void table(int b1){
        deleteArrangement();
        
            try {
                String url="jdbc:mysql://localhost:3306/project";
            String uname="root";
            String password="archana@2002";
            Connection conn = DriverManager.getConnection(url,uname,password);
                Statement st = conn.createStatement();
                int[] rollno=new int[3];
                String[] name=new String[3];
                for(int i=0;i<12;i++){
                    String r=room[i];
                    String sql2="SELECT seats FROM room where roomid="+r+"";
                    Statement st2=conn.createStatement();
                    ResultSet rs2=st2.executeQuery(sql2);
                    if(rs2.next()){
                        bench[i]=rs2.getInt(1);
                    }
                    else{
                        bench[i]=0;
                    }
                }
                for(int i=0;i<12;i++){
                    System.out.println(bench[i]);
                }
                System.out.println(b1);
                int k=0,l=0;
                for(int i=0;i<b;i++){
                    if(k==bench[l]){
                        k=0;
                        l++;
                    }
                    for(int j=0;j<3;j++){
                        String sql1="SELECT roll_no,name FROM student where register_no='"+ar[i][j]+"'";
                       
                        ResultSet rs=st.executeQuery(sql1);
                        if(rs.next()){
                            rollno[j]=rs.getInt(1);
                            name[j]=rs.getString(2);
                            System.out.println("name is"+name[j]);
                        }
                        else{
                            name[j]="Null";
                            rollno[j]=0;
                        }
                    }
                    String sql3="INSERT INTO arrangement(`room_no`, `c1_roll_no`, `c1_name`, `c2_roll_no`, `c2_name`, `c3_roll_no`, `c3_name`) VALUES (?,?,?,?,?,?,?)";
                    PreparedStatement stmt=conn.prepareStatement(sql3);
                    stmt.setString(1,room[l]);
                    stmt.setInt(2,rollno[0]);
                    stmt.setString(3,name[0]);
                    stmt.setInt(4,rollno[1]);
                    stmt.setString(5,name[1]);
                    stmt.setInt(6,rollno[2]);
                    stmt.setString(7,name[2]);
                    stmt.executeUpdate();
                    k++;
                }
            }catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
           
        }
    }
    
    public void printTable(){
      
            try {
                 String url="jdbc:mysql://localhost:3306/project";
            String uname="root";
            String password="archana@2002";
            Connection conn = DriverManager.getConnection(url,uname,password);
                Statement st = conn.createStatement();
                String sql="SELECT room_no, c1_roll_no, c1_name, c2_roll_no, c2_name, c3_roll_no, c3_name FROM arrangement";
                PreparedStatement stmt=conn.prepareStatement(sql);
                ResultSet rs=stmt.executeQuery(sql);
                 while(rs.next()){
                    String a = rs.getString("room_no");
                    String b =rs.getString("c1_roll_no");
                   String c =rs.getString("c1_name");
                   String d =rs.getString("c2_roll_no");
                   String e =rs.getString("c2_name");
                   String f =rs.getString("c3_roll_no");
                   String g =rs.getString("c2_name");
                    String tbData[]={a,b,c,d,e,f,g};
                   DefaultTableModel tm=(DefaultTableModel)viewtable.getModel();
                   tm.addRow(tbData);
                }
            } catch (Exception ex) {
             JOptionPane.showMessageDialog(null, ex);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        viewtable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        viewtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "room_no", "c1_roll_no", "c1_name", "c2_roll_no", "c2_name", "c3_roll_no", "c3_name"
            }
        ));
        jScrollPane1.setViewportView(viewtable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(166, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(viewalloc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewalloc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewalloc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewalloc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new viewalloc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable viewtable;
    // End of variables declaration//GEN-END:variables
}
