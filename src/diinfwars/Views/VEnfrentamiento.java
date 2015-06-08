/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars.Views;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JPanel;

/**
 * Vista de la pantalla Enfrentamiento
 * @author MonodetH
 * @see CEnfrentamiento
 */
public class VEnfrentamiento extends javax.swing.JFrame {
    // ATRIBUTOS
    /**MODO: 0 = nada, 1 = mover, 2 = atacar, 3 = as tactico, 5 = reclutar.*/
    private int modo = 0;
    /**Posición de la casilla seleccionada actualmente*/
    private int casillaSeleccionada[] = new int[2];
    /**Matriz que contiene la representacion de las unidades*/
    private JLabel[][] matrizUnidad = new JLabel[9][20];
    /**Matriz que contiene la representacion del rango de ataque o movimiento*/
    private JPanel[][] matrizRango = new JPanel[9][20];
    /**Matriz que contiene la representacion de los terrenos*/
    private JLabel[][] matrizMapa = new JLabel[9][20];
    /**Jugador activo*/
    private int jActivo = 1;
    
    // CONSTRUCTORES
    /**Constructor de la vista sin listeners*/
    public VEnfrentamiento() {
        initComponents();
        init();
    }
    /**
     * Constructor de la vista que recive dos listeners
     * @param al Controlador como ActionListener
     * @param ml Controlador como MouseListener
     */
    public VEnfrentamiento(ActionListener al,MouseListener ml) {
        initComponents();
        init();
        agregarActionListener(al);
        agregarMouseListener(ml);
    }

    // Metodos
    public void dibujarTerreno(String[][] casillas){
        for(int i=0;i<9;i++){
            for(int j=0;j<20;j++){
                matrizMapa[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource(casillas[i][j])));
            }
        }
    }
    
    public void dibujarUnidades(String[][] unidades){
        for(int i=0;i<9;i++){
            for(int j=0;j<20;j++){
                matrizUnidad[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource(unidades[i][j])));
            }
        }
    }
    
    public void dibujarRango(boolean[][] rango){
        // Ocultar rango        
        for(int i=0;i<9;i++){
            for(int j=0;j<20;j++){
                matrizRango[i][j].setBackground(new java.awt.Color(0, 0, 0, 0));
            }
        }
        if (this.modo == 0){return;} // Si solo se quiere ocultar el rango, la funcion termina aqui
        java.awt.Color color;
        if (this.modo == 1){color = new java.awt.Color(50, 50, 255, 80);}
        else if (this.modo == 2 || this.modo == 3){color = new java.awt.Color(255, 50, 50, 80);}
        else{color = new java.awt.Color(50, 255, 50, 80);}
        
        for(int i=0;i<9;i++){
            for(int j=0;j<20;j++){
                if(rango[i][j]){
                    matrizRango[i][j].setBackground(color);
                }
            }
        }        
    }
    
    
    
    
    // GETS Y SETS
    /**
     * Cambia la ventana de modo
     * @param modo 0 = nada, 1 = mover, 2 = atacar, 3 = as tactico, 5 = reclutar.
     * @see #setToggleOn(int)
     * @see #modo
     */
    public void setModo(int modo){if (this.modo == modo){this.modo =0;}else{this.modo=modo;setToggleOn(modo);}}
    public int getModo(){return this.modo;}
    /**
     * Guarda y activa la casilla seleccionada actual
     * @param i Posicion fila
     * @param j Posicion columna
     */
    public void setCasillaSeleccionada(int i,int j){
        matrizUnidad[casillaSeleccionada[0]][casillaSeleccionada[1]].setBorder(javax.swing.BorderFactory.createEmptyBorder());
        this.casillaSeleccionada[0]=i;
        this.casillaSeleccionada[1]=j;
        matrizUnidad[i][j].setBorder(new javax.swing.border.LineBorder(new java.awt.Color(250, 245, 0), 1, true));
    }
    public int[] getCasillaSeleccionada(){return this.casillaSeleccionada;}
    public int getJugador(){return this.jActivo;}
    public void toggleJugador(String nombre){
        if(this.jActivo == 1){this.jActivo=2;}
        else{this.jActivo=1;}
        setLabel2(nombre);
    }
    public void setLabel1(String str){this.jLabel1.setText(str);}
    public void setLabel2(String str){this.jLabel2.setText(str);}
    public JLabel[][] getMatrizUnidad(){return this.matrizUnidad;}
    /**
     * Cambia visualmente los botones de seleccion de modo
     * @param activado Modo activado
     * @see #modo
     */
    public void setToggleOn(int activado){
        this.botonMover.setSelected(false);
        this.botonAtacar.setSelected(false);
        this.botonAsTactico.setSelected(false);
        this.botonReclutar.setSelected(false);
        switch(activado){
            case 1:
                this.botonMover.setSelected(true);
                break;
            case 2:
                this.botonAtacar.setSelected(true);
                break;
            case 3:
                this.botonAsTactico.setSelected(true);
                break;
            case 4:
                this.botonReclutar.setSelected(true);
                break;
        }
    }
    
    
    private void agregarActionListener(ActionListener al){
        this.botonAtacar.addActionListener(al);
        this.botonMover.addActionListener(al);
        this.botonAsTactico.addActionListener(al);
        this.botonReclutar.addActionListener(al);
        this.botonFinalizarTurno.addActionListener(al);
        this.botonRendirse.addActionListener(al);
    }
    
    private void agregarMouseListener(MouseListener ml){
        for (int i=0;i<9;i++){
            for (int j=0;j<20;j++){
                this.matrizUnidad[i][j].addMouseListener(ml);
            }
        }
    }
    
    
    /**Inicializa los componentes que no fueron creados a través del editor de Netbeans*/
    private void init(){
        for (int i=0;i<9;i++){
            for (int j=0;j<20;j++){
                matrizMapa[i][j]= new JLabel();
                matrizMapa[i][j].setBorder(new javax.swing.border.LineBorder(new java.awt.Color(150, 150, 150), 1, true));
                matrizRango[i][j]= new JPanel();
                matrizRango[i][j].setBackground(new java.awt.Color(0, 0, 0, 0));
                matrizUnidad[i][j]= new JLabel();
                capaUnidad.add(matrizUnidad[i][j]);
                capaRango.add(matrizRango[i][j]);
                capaMapa.add(matrizMapa[i][j]);
            }
        }
        // imprimir mapa
        matrizUnidad[2][3].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SpriteAlumnoAzul.png")));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Tablero = new javax.swing.JLayeredPane();
        capaMapa = new javax.swing.JPanel();
        capaRango = new javax.swing.JPanel();
        capaUnidad = new javax.swing.JPanel();
        panelInfo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        botonMover = new javax.swing.JToggleButton();
        botonAtacar = new javax.swing.JToggleButton();
        botonAsTactico = new javax.swing.JToggleButton();
        botonReclutar = new javax.swing.JToggleButton();
        botonFinalizarTurno = new javax.swing.JButton();
        botonRendirse = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DIInfWars - Enfrentamiento");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusCycleRoot(false);
        setFocusableWindowState(false);
        setForeground(java.awt.Color.darkGray);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Tablero.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Tablero.setPreferredSize(new java.awt.Dimension(650, 290));
        Tablero.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        capaMapa.setLayout(new java.awt.GridLayout(0, 20));
        Tablero.add(capaMapa, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 650, 290));
        Tablero.setLayer(capaMapa, 1);

        capaRango.setOpaque(false);
        capaRango.setLayout(new java.awt.GridLayout(0, 20));
        Tablero.add(capaRango, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 650, 290));
        Tablero.setLayer(capaRango, 2);

        capaUnidad.setOpaque(false);
        capaUnidad.setLayout(new java.awt.GridLayout(0, 20));
        Tablero.add(capaUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 650, 290));
        Tablero.setLayer(capaUnidad, 3);

        getContentPane().add(Tablero, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 660, 300));

        panelInfo.setBackground(new java.awt.Color(255, 255, 255));
        panelInfo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        javax.swing.GroupLayout panelInfoLayout = new javax.swing.GroupLayout(panelInfo);
        panelInfo.setLayout(panelInfoLayout);
        panelInfoLayout.setHorizontalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
        );
        panelInfoLayout.setVerticalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 236, Short.MAX_VALUE)
        );

        getContentPane().add(panelInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 660, 240));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Turno de");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 100, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Jugador 1");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 40, -1, -1));

        botonMover.setText("Mover");
        getContentPane().add(botonMover, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 70, 110, 70));

        botonAtacar.setText("Atacar");
        getContentPane().add(botonAtacar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 150, 110, 70));

        botonAsTactico.setText("As Táctico");
        getContentPane().add(botonAsTactico, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 230, 110, 70));

        botonReclutar.setText("Reclutar");
        getContentPane().add(botonReclutar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 320, 110, 70));

        botonFinalizarTurno.setText("Finalizar Turno");
        botonFinalizarTurno.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonFinalizarTurno.setMargin(new java.awt.Insets(2, 2, 2, 2));
        getContentPane().add(botonFinalizarTurno, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 410, 110, 120));

        botonRendirse.setText("Rendirse");
        botonRendirse.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(botonRendirse, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 540, 110, -1));

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
            java.util.logging.Logger.getLogger(VEnfrentamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VEnfrentamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VEnfrentamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VEnfrentamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VEnfrentamiento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane Tablero;
    private javax.swing.JToggleButton botonAsTactico;
    private javax.swing.JToggleButton botonAtacar;
    private javax.swing.JButton botonFinalizarTurno;
    private javax.swing.JToggleButton botonMover;
    private javax.swing.JToggleButton botonReclutar;
    private javax.swing.JButton botonRendirse;
    private javax.swing.JPanel capaMapa;
    private javax.swing.JPanel capaRango;
    private javax.swing.JPanel capaUnidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel panelInfo;
    // End of variables declaration//GEN-END:variables
}
