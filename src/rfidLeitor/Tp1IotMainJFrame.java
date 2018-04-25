package rfidLeitor;

import com.alien.enterpriseRFID.reader.AlienReaderException;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author guilhermehenrique
 */
public class Tp1IotMainJFrame extends javax.swing.JFrame {
	
	
	Tp1IotReader reader;
	javax.swing.table.DefaultTableModel model;
	DialogConfig dialogConfig;
	
    /**
     * Creates new form tp1IotMainJFrame
     */
    public Tp1IotMainJFrame() throws AlienReaderException {
    	reader = new Tp1IotReader();
    	
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() throws AlienReaderException {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        labelModo = new javax.swing.JLabel();
        comboBoxModo = new javax.swing.JComboBox<>();
        labelEfeito = new javax.swing.JLabel();
        comboBoxEfeito = new javax.swing.JComboBox<>();
        labelTimeOut = new javax.swing.JLabel();
        textfieldTimeOut = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableData = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        buttonIniciar = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        buttonParar = new javax.swing.JButton();
        buttonConfig = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        distancia = new javax.swing.JTextField();
        model = new javax.swing.table.DefaultTableModel(new String[] {"ID",
        															  "Antena",
        															  "Taxa de Leitura (Média)",
        															  "Distância Real",
        															  "Distância Estimada",
        															  "RSSI"}, 0);
        dialogConfig = new DialogConfig(this, true);
        
        jInternalFrame1.setVisible(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        this.reader.setCaminholog(dialogConfig.getLogDir());

        labelModo.setText("Modo:");

        comboBoxModo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativo", "Autônomo" }));
        comboBoxModo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxModoActionPerformed(evt);
            }
        });

        labelEfeito.setText("Efeito:");

        comboBoxEfeito.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nenhum", "Água", "Metal" }));
        comboBoxEfeito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxEfeitoActionPerformed(evt);
            }
        });
        
        this.reader.setEfeito("Nenhum");
        this.reader.setModo("Ativo");

        labelTimeOut.setText("Time out (s)");
        
        this.reader.setTimeout(0);

        textfieldTimeOut.setText("60");
        textfieldTimeOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldTimeOutActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)); 
        
        tableData.setModel(model);
        
        jScrollPane1.setViewportView(tableData);

        jLabel1.setText("INTERNET DAS COISAS - TRABALHO PRÁTICO 1 ");

        jLabel2.setText("MEDIÇÕES DE RFID - ALIEN 9650");

        jLabel3.setText("GRUPO: Andre, Guilherme Henrique e Gilson");

        jLayeredPane2.setLayout(new java.awt.GridLayout(1, 0));

        buttonIniciar.setText("Iniciar");
        buttonIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					buttonIniciarActionPerformed(evt);
				} catch (IOException | AlienReaderException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        jLayeredPane2.add(buttonIniciar);

        buttonCancel.setText("Cancelar");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });
        jLayeredPane2.add(buttonCancel);

        buttonParar.setText("Parar");
        buttonParar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPararActionPerformed(evt);
            }
        });

        buttonConfig.setText("Configuração");
        buttonConfig.setToolTipText("");
        buttonConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfigActionPerformed(evt);
            }
        });

        jLabel4.setText("Distancia (M)");

        distancia.setText("1");
        
        distancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	distanciaOutActionPerformed(evt);
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(labelModo)
                            .addGap(18, 18, 18)
                            .addComponent(comboBoxModo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(52, 52, 52)
                            .addComponent(labelEfeito)
                            .addGap(43, 43, 43)
                            .addComponent(comboBoxEfeito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(49, 49, 49)
                            .addComponent(labelTimeOut)
                            .addGap(29, 29, 29)
                            .addComponent(textfieldTimeOut, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(35, 35, 35)
                            .addComponent(jLabel4)
                            .addGap(29, 29, 29)
                            .addComponent(distancia, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonParar)
                            .addGap(35, 35, 35)
                            .addComponent(buttonConfig))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1175, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addComponent(jLabel2)
                .addGap(6, 6, 6)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(labelModo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(comboBoxModo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(labelEfeito))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(comboBoxEfeito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(labelTimeOut))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textfieldTimeOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(distancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonParar)
                        .addComponent(buttonConfig)))
                .addGap(49, 49, 49)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setBounds(0, 0, 1206, 636);
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxModoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxModoActionPerformed
    	
    	reader.setModo(comboBoxModo.getSelectedItem().toString());
    }

    private void comboBoxEfeitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxEfeitoActionPerformed
    	
    	reader.setEfeito(comboBoxEfeito.getSelectedItem().toString());
        
    }//GEN-LAST:event_comboBoxEfeitoActionPerformed

    private void buttonIniciarActionPerformed(java.awt.event.ActionEvent evt) throws IOException, AlienReaderException {//GEN-FIRST:event_buttonIniciarActionPerformed
        // TODO add your handling code here:
    	reader.setCaminholog(dialogConfig.getjTextFieldLog().getText());
    	reader.setIpLeitor(dialogConfig.getjTextFieldIp().getText());
    	reader.setUsernameLeitor(dialogConfig.getjTextFieldUser().getText());
    	reader.setPassWordLeitor(dialogConfig.getjPasswordFieldPass().getText());
    	reader.setDistancia(distancia.getText());
    	
        if (this.comboBoxModo.getSelectedItem().toString() == "Ativo") {
        	
        	reader.setPortaLeitor(Integer.parseInt(dialogConfig.getjTextFieldActPort().getText()));
        	
			Thread t = new Thread() {
				
				public void run() {
		    		buttonIniciar.setEnabled(false);
		    		
		    		try {
						reader.lerAtivamente();
					} catch (AlienReaderException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
		    		
		            Item[] tags = reader.getResults();
		            
		            int numTags  = reader.numTags();
		            int rowCount = model.getRowCount();
		            
		            for (int i = 0; i < rowCount; i++) {
		            	
		            	model.removeRow(0);
		            	
		            }
		            
		            model.fireTableStructureChanged();
		            
		            for(Item tag : tags) {
		            	
		            	model.addRow(new Object[] {tag.id, tag.antenna, tag.reads, tag.distanceReal, tag.distanceEst, tag.rssi });
		            	
		            }
		            
		            buttonIniciar.setEnabled(true);
				}
			};
			t.start();
		    
		} else {
			
			Thread t = new Thread() {
				public void run() {
					
					buttonIniciar.setEnabled(false);
					
					try {
						reader.setTimeout(Float.parseFloat(textfieldTimeOut.getText()));
						reader.setPortaAuto(Float.parseFloat(dialogConfig.getjTextFieldAutoPort().getText()));
						reader.lerAuto();
					} catch (AlienReaderException | IOException | InterruptedException e) {
						e.printStackTrace();
					}
		    		
		    		buttonIniciar.setEnabled(true);
				}
			};
			t.start();
			
		}
    }//GEN-LAST:event_buttonIniciarActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {
    	System.exit(0);
    }

    private void textfieldTimeOutActionPerformed(java.awt.event.ActionEvent evt) {
    		
    }
    
    private void distanciaOutActionPerformed(java.awt.event.ActionEvent evt) {
		
    }

    private void buttonPararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPararActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonPararActionPerformed

    private void buttonConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfigActionPerformed
   
	    dialogConfig.setVisible(true);
	    dialogConfig.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    }

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonConfig;
    private javax.swing.JButton buttonIniciar;
    private javax.swing.JButton buttonParar;
    private javax.swing.JComboBox<String> comboBoxEfeito;
    private javax.swing.JComboBox<String> comboBoxModo;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelEfeito;
    private javax.swing.JLabel labelModo;
    private javax.swing.JLabel labelTimeOut;
    private javax.swing.JTable tableData;
    private javax.swing.JTextField textfieldTimeOut;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField distancia;
    // End of variables declaration//GEN-END:variables
}
