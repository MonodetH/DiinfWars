/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars.Views;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JButton;
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
                if(unidades[i][j] != null){
                    System.out.println(unidades[i][j]);
                    matrizUnidad[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource(unidades[i][j])));
                }else{
                    matrizUnidad[i][j].setIcon(null);
                }
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
    public JButton getReclutarAlumno(){return this.bReclutarAlumno;}
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
    
    public boolean existeUnidad(int fila,int col){
        return !(matrizUnidad[fila][col].getIcon() == null);
    }
    public boolean enRango(int fila, int col){
        if (matrizRango[fila][col].getBackground().equals(new java.awt.Color(0, 0, 0, 0))){
            return false;
        }
        return true;
    }
    
    public void mostrarMenuReclutar(int oro) {
        
    }
    
    
    private void agregarActionListener(ActionListener al){
        this.botonAtacar.addActionListener(al);
        this.botonMover.addActionListener(al);
        this.botonAsTactico.addActionListener(al);
        this.botonReclutar.addActionListener(al);
        this.botonFinalizarTurno.addActionListener(al);
        this.botonRendirse.addActionListener(al);
        this.bReclutarAlumno.addActionListener(al);
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
        //matrizUnidad[2][3].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SpriteAlumnoAzul.png")));
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
        Info = new javax.swing.JLayeredPane();
        infoReclutar1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        bReclutarAlumno = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
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
        setAlwaysOnTop(true);
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

        Info.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        infoReclutar1.setBackground(new java.awt.Color(255, 255, 255));
        infoReclutar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jButton2.setText("Reclutar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Reclutar");

        jButton4.setText("Reclutar");

        jButton5.setText("Reclutar");

        jButton6.setText("Reclutar");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Alumno");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Alumno Superior");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Ayudante");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Cachorro");

        jLabel10.setText("Coordinador Ayudantes");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Pame");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SpriteAlumnoSuperior.png"))); // NOI18N

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SpriteAyudante.png"))); // NOI18N

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SpriteCachorro.png"))); // NOI18N

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SpriteCoordinador.png"))); // NOI18N
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SpritePame.png"))); // NOI18N

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Costo: 6");

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Costo: 8");

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Costo: 8");

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Costo: 4");

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Costo: 10");

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Costo: 12");

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SpriteAlumno.png"))); // NOI18N

        bReclutarAlumno.setText("Reclutar");

        javax.swing.GroupLayout infoReclutar1Layout = new javax.swing.GroupLayout(infoReclutar1);
        infoReclutar1.setLayout(infoReclutar1Layout);
        infoReclutar1Layout.setHorizontalGroup(
            infoReclutar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoReclutar1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoReclutar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(infoReclutar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addGroup(infoReclutar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(infoReclutar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bReclutarAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        infoReclutar1Layout.setVerticalGroup(
            infoReclutar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoReclutar1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoReclutar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bReclutarAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoReclutar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoReclutar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoReclutar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoReclutar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoReclutar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoReclutar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(9, 9, 9))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Información");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout InfoLayout = new javax.swing.GroupLayout(Info);
        Info.setLayout(InfoLayout);
        InfoLayout.setHorizontalGroup(
            InfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InfoLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(infoReclutar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        InfoLayout.setVerticalGroup(
            InfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(infoReclutar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Info.setLayer(infoReclutar1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Info.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        getContentPane().add(Info, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 660, 240));

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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
    private javax.swing.JLayeredPane Info;
    private javax.swing.JLayeredPane Tablero;
    private javax.swing.JButton bReclutarAlumno;
    private javax.swing.JToggleButton botonAsTactico;
    private javax.swing.JToggleButton botonAtacar;
    private javax.swing.JButton botonFinalizarTurno;
    private javax.swing.JToggleButton botonMover;
    private javax.swing.JToggleButton botonReclutar;
    private javax.swing.JButton botonRendirse;
    private javax.swing.JPanel capaMapa;
    private javax.swing.JPanel capaRango;
    private javax.swing.JPanel capaUnidad;
    private javax.swing.JPanel infoReclutar1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    
}
