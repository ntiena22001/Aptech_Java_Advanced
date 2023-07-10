/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package JAVASWING;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.validation.Validator;
import Entity.Customer;
import jdbc.JDBC;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ManagementCustomer extends javax.swing.JFrame {

    private JDBC jdbc;
    private List<Customer> customers;
    private DefaultTableModel tableModel;
    private JTable table;

    /**
     * Creates new form ManagementCustomer
     */
    public ManagementCustomer() throws IOException {
        jdbc = new JDBC();
        try {
            jdbc.jdbcConnectDB();
            customers = jdbc.getAllCustomer();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        initComponents();
        //Hiển thị dữ liệu vào bảng
        show_database();

    }

    //Đổ dữ liệu vào table
    public void show_database() {
        DefaultTableModel model = (DefaultTableModel) jTable_Management_Customer.getModel();
        for (Customer customer : customers) {
            model.addRow(new Object[]{
                customer.getCustomerID(),
                customer.getCompanyName(),
                customer.getContactName(),
                customer.getContactTitle(),
                customer.getAddress(),
                customer.getCity(),
                customer.getRegion(),
                customer.getPostalCode(),
                customer.getCountry(),
                customer.getPhone(),
                customer.getFax()
            });
        }
    }

    public void saveButton() throws SQLException {
        int rowIndex = jTable_Management_Customer.getSelectedRow();
        if (rowIndex >= 0 && rowIndex < customers.size()) {
            String customerID = (String) tableModel.getValueAt(rowIndex, 0);
            String companyName = jTextField2.getText();
            String contactName = jTextField3.getText();
            String contactTitle = jTextField4.getText();
            String address = jTextField5.getText();
            String city = jTextField6.getText();
            String region = jTextField7.getText();
            String postalCode = jTextField8.getText();
            String country = jTextField9.getText();
            String phone = jTextField10.getText();
            String fax = jTextField11.getText();

            Customer customer = customers.get(rowIndex);
            if (companyName == null || contactName == null || contactTitle == null || address == null || city == null || region == null || postalCode == null
                    || country == null || phone == null || fax == null) {
                customer.setCustomerID(customerID);
                customer.setCompanyName(companyName);
                customer.setContactName(contactName);
                customer.setContactTitle(contactTitle);
                customer.setAddress(address);
                customer.setCity(city);
                customer.setRegion(region);
                customer.setPostalCode(postalCode);
                customer.setCountry(country);
                customer.setPhone(phone);
                customer.setFax(fax);
            } else {
                customer.setCustomerID(customerID);
                customer.setCompanyName(companyName);
                customer.setContactName(contactName);
                customer.setContactTitle(contactTitle);
                customer.setAddress(address);
                customer.setCity(city);
                customer.setRegion(region);
                customer.setPostalCode(postalCode);
                customer.setCountry(country);
                customer.setPhone(phone);
                customer.setFax(fax);
            }
            boolean updateSave = jdbc.updateByID(customer);
            if (updateSave) {
                JOptionPane.showMessageDialog(this, "Update is successful");
                tableModel.setValueAt(companyName, rowIndex, 1);
                tableModel.setValueAt(contactName, rowIndex, 2);
                tableModel.setValueAt(contactTitle, rowIndex, 3);
                tableModel.setValueAt(address, rowIndex, 4);
                tableModel.setValueAt(city, rowIndex, 5);
                tableModel.setValueAt(region, rowIndex, 6);
                tableModel.setValueAt(postalCode, rowIndex, 7);
                tableModel.setValueAt(country, rowIndex, 8);
                tableModel.setValueAt(phone, rowIndex, 9);
                tableModel.setValueAt(fax, rowIndex, 10);
            } else {
                JOptionPane.showMessageDialog(this, "Update is failed");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a customer from the table.");
        }
    }

    public void updateButton() {
        tableModel = (DefaultTableModel) jTable_Management_Customer.getModel();
        table = jTable_Management_Customer;
        //Lấy giá trị đang chọn và lấy dữ liệu trọng các cột trong danh sách
        int indexRow = jTable_Management_Customer.getSelectedRow();
        for (int i = 0; i < customers.size(); i++) {
            if (indexRow == i) {
                String customerID = (String) tableModel.getValueAt(indexRow, 0);
                String companyName = (String) tableModel.getValueAt(indexRow, 1);
                String contactName = (String) tableModel.getValueAt(indexRow, 2);
                String contactTitle = (String) tableModel.getValueAt(indexRow, 3);
                String address = (String) tableModel.getValueAt(indexRow, 4);
                String city = (String) tableModel.getValueAt(indexRow, 5);
                String region = (String) tableModel.getValueAt(indexRow, 6);
                String postalCode = (String) tableModel.getValueAt(indexRow, 7);
                String country = (String) tableModel.getValueAt(indexRow, 8);
                String phone = (String) tableModel.getValueAt(indexRow, 9);
                String fax = (String) tableModel.getValueAt(indexRow, 10);
                //Đổ dữ liệu trở lại bảng
                jTextField1.setText(customerID);
                jTextField1.setEnabled(false);
                jTextField2.setText(companyName);
                jTextField3.setText(contactName);
                jTextField4.setText(contactTitle);
                jTextField5.setText(address);
                jTextField6.setText(city);
                jTextField7.setText(region);
                jTextField8.setText(postalCode);
                jTextField9.setText(country);
                jTextField10.setText(phone);
                jTextField11.setText(fax);
            }

        }
    }

    public void addButton() throws ClassNotFoundException {
        String customerID = jTextField1.getText();
        String companyName = jTextField2.getText();
        String contactName = jTextField3.getText();
        String contactTitle = jTextField4.getText();
        String address = jTextField5.getText();
        String city = jTextField6.getText();
        String region = jTextField7.getText();
        String postalCode = jTextField8.getText();
        String country = jTextField9.getText();
        String phone = jTextField10.getText();
        String fax = jTextField11.getText();

        // Kiểm tra tính hợp lệ của dữ liệu
        StringBuilder stringBuilder = new StringBuilder();
        //Kiểm tra các trường, nếu trống thì điền vào!
        if (customerID.isEmpty() || companyName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out all the required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Kiểm tra ký tự nếu dài hơn 5 thì phải nhập lại   
        if (customerID.length() > 5) {
            JOptionPane.showMessageDialog(this, "CustomerID should be no longer than 5 characters.", "Error", JOptionPane.ERROR_MESSAGE);
            jTextField1.setText("");
            return;
        }
        try {
            Customer customerIdExist = jdbc.getCustomerByID(customerID);
            if (customerIdExist != null) {
                JOptionPane.showMessageDialog(this, "CustomerID already exists. Cannot create a new customer.", "Error", JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(this, "Please create a new Customer again", "Error", JOptionPane.ERROR_MESSAGE);
                jTextField1.setText("");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Customer newCustomer = new Customer(customerID, companyName, contactName, contactTitle, address, city, region, postalCode, country, phone, fax);
        try {
            boolean addNewCustomer = jdbc.addNewCustomer(newCustomer);
            if (addNewCustomer) {
                tableModel = (DefaultTableModel) jTable_Management_Customer.getModel();
                // Thêm khách hàng mới vào danh sách customers
                customers.add(newCustomer);

                // Thêm dòng mới vào tableModel
                tableModel.addRow(new Object[]{
                    newCustomer.getCustomerID().trim(),
                    newCustomer.getCompanyName().trim(),
                    newCustomer.getContactName().trim(),
                    newCustomer.getContactTitle().trim(),
                    newCustomer.getAddress().trim(),
                    newCustomer.getCity().trim(),
                    newCustomer.getRegion().trim(),
                    newCustomer.getPostalCode().trim(),
                    newCustomer.getCountry().trim(),
                    newCustomer.getPhone().trim(),
                    newCustomer.getFax().trim()
                });

                // Cập nhật lại hiển thị của bảng
                tableModel.fireTableDataChanged();

                JOptionPane.showMessageDialog(this, "Customer added successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add customer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding customer to the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void resetForm() {
        jTextField1.setText("");
        jTextField1.setEnabled(rootPaneCheckingEnabled);
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jTextField10.setText("");
        jTextField11.setText("");
    }

    public void deleteButton() throws SQLException, ClassNotFoundException {
        tableModel = (DefaultTableModel) jTable_Management_Customer.getModel();
        int rowIndex = jTable_Management_Customer.getSelectedRow();
        if (rowIndex >= 0 && rowIndex < customers.size()) {
            String customerID = (String) tableModel.getValueAt(rowIndex, 0);
            Customer customer = customers.get(rowIndex);
            customer.setCustomerID(customerID);

            boolean deleteID = jdbc.deleteCustomerByID(customer.getCustomerID());
            if (deleteID) {
                JOptionPane.showMessageDialog(rootPane, "Delete is successful");
                customers.remove(rowIndex);
                tableModel.removeRow(rowIndex);
                tableModel.fireTableDataChanged();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Delete is failed");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "No customer selected");
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        ADDBUTTON = new javax.swing.JButton();
        SAVEBUTTON = new javax.swing.JButton();
        DELETEBUTTON = new javax.swing.JButton();
        UPDATEBUTTON = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Management_Customer = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 204));
        jLabel1.setText("MANAGEMENT CUSTOMER");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("CUSTOMERID:");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("COMPANYNAME:");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("CONTACTNAME:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("CONTACTTITLE:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("ADDRESS:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("CITY:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("REGION:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("POSTALCODE:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("COUNTRY:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("PHONE:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("FAX:");

        ADDBUTTON.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Create.png"))); // NOI18N
        ADDBUTTON.setText("ADD");
        ADDBUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ADDBUTTONActionPerformed(evt);
            }
        });

        SAVEBUTTON.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Save.png"))); // NOI18N
        SAVEBUTTON.setText("SAVE");
        SAVEBUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SAVEBUTTONActionPerformed(evt);
            }
        });

        DELETEBUTTON.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Delete.png"))); // NOI18N
        DELETEBUTTON.setText("DELETE");
        DELETEBUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DELETEBUTTONActionPerformed(evt);
            }
        });

        UPDATEBUTTON.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Edit.png"))); // NOI18N
        UPDATEBUTTON.setText("UPDATE");
        UPDATEBUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UPDATEBUTTONActionPerformed(evt);
            }
        });
        UPDATEBUTTON.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                UPDATEBUTTONKeyReleased(evt);
            }
        });

        jTable_Management_Customer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CustomerID", "CompanyName", "ContactName", "ContactTitle", "Address", "City", "Region", "PostalCode", "Country", "Phone", "Fax"
            }
        ));
        jTable_Management_Customer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Management_CustomerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Management_Customer);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(311, 311, 311)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField10, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField4)
                            .addComponent(jTextField11))))
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ADDBUTTON, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DELETEBUTTON, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SAVEBUTTON, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(UPDATEBUTTON, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                .addGap(105, 105, 105))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1015, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ADDBUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SAVEBUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(UPDATEBUTTON, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(DELETEBUTTON, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void ADDBUTTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ADDBUTTONActionPerformed
        try {
            addButton();
            resetForm();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManagementCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_ADDBUTTONActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void UPDATEBUTTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UPDATEBUTTONActionPerformed
        updateButton();

    }//GEN-LAST:event_UPDATEBUTTONActionPerformed

    private void UPDATEBUTTONKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UPDATEBUTTONKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_UPDATEBUTTONKeyReleased

    private void SAVEBUTTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SAVEBUTTONActionPerformed
        try {
            saveButton();
            resetForm();
        } catch (SQLException ex) {
            Logger.getLogger(ManagementCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SAVEBUTTONActionPerformed

    private void jTable_Management_CustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_Management_CustomerMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable_Management_CustomerMouseClicked

    private void DELETEBUTTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DELETEBUTTONActionPerformed
        try {
            deleteButton();
            show_database();
        } catch (SQLException ex) {
            Logger.getLogger(ManagementCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManagementCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_DELETEBUTTONActionPerformed

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
            java.util.logging.Logger.getLogger(ManagementCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagementCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagementCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagementCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ManagementCustomer().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(ManagementCustomer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ADDBUTTON;
    private javax.swing.JButton DELETEBUTTON;
    private javax.swing.JButton SAVEBUTTON;
    private javax.swing.JButton UPDATEBUTTON;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Management_Customer;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
