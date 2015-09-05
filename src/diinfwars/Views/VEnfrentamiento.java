/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars.Views;

import diinfwars.Models.Unidades.Alumno;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.ListSelectionListener;

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
    /**Posición de la casilla seleccionada como objetivo*/
    private int casillaObjetivo[] = new int[2];
    /**Matriz que contiene la representacion de las unidades*/
    private JLabel[][] matrizUnidad = new JLabel[9][20];
    /**Matriz que contiene la representacion del rango de ataque o movimiento*/
    private JPanel[][] matrizRango = new JPanel[9][20];
    /**Matriz que contiene la representacion de los terrenos*/
    private JLabel[][] matrizMapa = new JLabel[9][20];
    /**Jugador activo*/
    private int jActivo = 2;
    
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
    public VEnfrentamiento(ActionListener al,MouseListener ml,ListSelectionListener ll) {
        initComponents();
        init();
        setModo(0);
        setCasillaSeleccionada(4, 0);
        clearCasillaObjetivo();
        agregarActionListener(al);
        agregarMouseListener(ml);
        agregarListSelectionListener(ll);
        this.setLocationRelativeTo(null);
    }

    // METODOS
    public void toggleJugador(String nombre){
        if(this.jActivo == 1){this.jActivo=2;}
        else{this.jActivo=1;}
        setLabel2(nombre);
    }
    public void clearCasillaObjetivo(){
        if(this.casillaObjetivo[0] != -1 && this.casillaObjetivo[1] != -1){
            matrizUnidad[casillaObjetivo[0]][casillaObjetivo[1]].setBorder(javax.swing.BorderFactory.createEmptyBorder());
            this.casillaObjetivo[0]=-1;
            this.casillaObjetivo[1]=-1;
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
    
    public void actualizarPanelReclutar(int oro) {
        this.bReclutarAlumno.setEnabled(oro >= 6);
        this.bReclutarAyudante.setEnabled(oro >= 8);
        this.bReclutarCachorro.setEnabled(oro >= 4);
        this.bReclutarCoordinador.setEnabled(oro >= 10);
        this.bReclutarPame.setEnabled(oro >= 12);
        this.bReclutarSuperior.setEnabled(oro >= 8);
        
        this.jLabel27.setText(String.valueOf(oro));
    } 
    
    // Dibujar GUI
    public void dibujarTerreno(String[][] casillas){
        for(int i=0;i<9;i++){
            for(int j=0;j<20;j++){
                matrizMapa[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource(casillas[i][j])));
            }
        }
    }
    public void actualizarTerrenoToolTip(String[][] toolTips){
        /* 
         * IMPLEMENTAR
         */
        for(int i=0;i<9;i++){
            for(int j=0;j<20;j++){
                matrizUnidad[i][j].setToolTipText(toolTips[i][j]);
            }
        }
    }
    
    public void dibujarUnidades(String[][] unidades){
        for(int i=0;i<9;i++){
            for(int j=0;j<20;j++){
                if(unidades[i][j] != null){
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
    
    
    
    
    // SETS
    /**
     * Cambia la ventana de modo
     * @param modo 0 = nada, 1 = mover, 2 = atacar, 3 = as tactico, 5 = reclutar.
     * @see #setToggleOn(int)
     * @see #modo
     */
    public void setModo(int modo){
        this.capaAsTactico.setVisible(false);
        this.capaAtacar.setVisible(false);
        this.capaMover.setVisible(false);
        this.capaReclutar.setVisible(false);
        
        if (this.modo == modo || modo == 0){this.modo =0;setToggleOn(0);}
        else{
            if(modo == 1){this.capaMover.setVisible(true);}
            else if (modo == 2){this.capaAtacar.setVisible(true);}
            else if (modo == 3){this.capaAsTactico.setVisible(true);}
            else{this.capaReclutar.setVisible(true);}
            this.modo=modo;
            setToggleOn(modo);
        }
    }
    public void setCasillaSeleccionada(int i,int j){
        matrizUnidad[casillaSeleccionada[0]][casillaSeleccionada[1]].setBorder(javax.swing.BorderFactory.createEmptyBorder());
        this.casillaSeleccionada[0]=i;
        this.casillaSeleccionada[1]=j;
        matrizUnidad[i][j].setBorder(new javax.swing.border.LineBorder(new java.awt.Color(250, 245, 0), 1, true));
    }
    public void setCasillaObjetivo(int i,int j){
        if(i != -1 && j != -1){
            if(this.casillaObjetivo[0] != -1 && this.casillaObjetivo[1] != -1){
                matrizUnidad[casillaObjetivo[0]][casillaObjetivo[1]].setBorder(javax.swing.BorderFactory.createEmptyBorder());
            }
            this.casillaObjetivo[0]=i;
            this.casillaObjetivo[1]=j;
            matrizUnidad[i][j].setBorder(new javax.swing.border.LineBorder(new java.awt.Color(200, 200, 0), 1, true));
        }
    }
    public void setListaAtaques(String[] listaAtaques){
        String[] lista = {"Corto - 0 - 0","Medio - 0 - 0","Largo - 0 - 0"};
        if(listaAtaques != null){lista = listaAtaques;}
        this.listaAtaque.setListData(lista);
        this.listaAtaque.setSelectedIndex(0);
        this.listaAtaque.ensureIndexIsVisible(0);  
    }
    
    
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
    public void setEnableAtacar(boolean enable){this.bConfirmarAtaque.setEnabled(enable);}
    public void setLabel1(String str){this.jLabel1.setText(str);}
    public void setLabel2(String str){this.jLabel2.setText(str);}
    
    
    // GETS
    public int getRangoAtaque(){
        String selected = (String) this.listaAtaque.getSelectedValue();
        String rango = (selected == null)? "Corto":selected.split(" - ")[0];
        
        if("Corto".equals(rango)){return 1;}
        else if("Medio".equals(rango)){return 2;}
        else if("Largo".equals(rango)){return 3;}
        return 1;
    }
    public int[] getAtaqueSeleccionado(){
        int[] ataque = new int[3];
        String selected = (String) this.listaAtaque.getSelectedValue();
        
        // Rango
        String rango = selected.split(" - ")[0];
        if("Corto".equals(rango)){ataque[0]=1;}
        else if("Medio".equals(rango)){ataque[0]=2;}
        else if("Largo".equals(rango)){ataque[0]=3;}
        // Daño
        ataque[1] = Integer.parseInt(selected.split(" - ")[1]);
        // Golpes
        ataque[2] = Integer.parseInt(selected.split(" - ")[2]);
        
        return ataque;
    }
    

    
    public int getModo(){return this.modo;}
    public int[] getCasillaSeleccionada(){return this.casillaSeleccionada;}
    public int[] getCasillaObjetivo(){return this.casillaObjetivo;}
    public int getJugador(){return this.jActivo;}
    public JLabel[][] getMatrizUnidad(){return this.matrizUnidad;}
    // Get GUI component
    public JPanel getPanelReclutar(){return this.panelReclutar;}
    public JButton getReclutarAlumno(){return this.bReclutarAlumno;}
    public JButton getReclutarCachorro(){return this.bReclutarCachorro;}
    public JButton getReclutarSuperior(){return this.bReclutarSuperior;}
    public JButton getReclutarAyudante(){return this.bReclutarAyudante;}
    public JButton getReclutarCoordinador(){return this.bReclutarCoordinador;}
    public JButton getReclutarPame(){return this.bReclutarPame;}
    public JButton getBConfirmarAtaque(){return this.bConfirmarAtaque;}
    public JButton getBotonSalir(){return this.botonSalir;}
    public JButton getBotonFinalizarTurno(){return this.botonFinalizarTurno;}
    public JButton getBotonRendirse(){return this.botonRendirse;}
    public JToggleButton getBotonMover(){return this.botonMover;}
    public JToggleButton getBotonAtacar(){return this.botonAtacar;}
    public JToggleButton getBotonReclutar(){return this.botonReclutar;}
    public JToggleButton getBotonAsTactico(){return this.botonAsTactico;}
    public JTextField gettextoGanador(){return this.textoGanador;}
    public JTextArea getTextoAtacar(){return this.TextoAtacar;}
    public JTextArea getTextoReclutar(){return this.TextoReclutar;}

    
    // LISTENERS
    private void agregarActionListener(ActionListener al){
        this.botonAtacar.addActionListener(al);
        this.botonMover.addActionListener(al);
        this.botonAsTactico.addActionListener(al);
        this.botonReclutar.addActionListener(al);
        this.botonFinalizarTurno.addActionListener(al);
        this.botonRendirse.addActionListener(al);
        this.botonSalir.addActionListener(al);

        this.bReclutarAlumno.addActionListener(al);
        this.bReclutarAyudante.addActionListener(al);
        this.bReclutarCachorro.addActionListener(al);
        this.bReclutarCoordinador.addActionListener(al);
        this.bReclutarPame.addActionListener(al);
        this.bReclutarSuperior.addActionListener(al);
        this.bConfirmarAtaque.addActionListener(al);
    }
    
    private void agregarMouseListener(MouseListener ml){
        for (int i=0;i<9;i++){
            for (int j=0;j<20;j++){
                this.matrizUnidad[i][j].addMouseListener(ml);
            }
        }
    }
    
    private void agregarListSelectionListener(ListSelectionListener ll){
        this.listaAtaque.addListSelectionListener(ll);
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

        jFrame1 = new javax.swing.JFrame();
        Tablero = new javax.swing.JLayeredPane();
        capaMapa = new javax.swing.JPanel();
        capaRango = new javax.swing.JPanel();
        capaUnidad = new javax.swing.JPanel();
        Info = new javax.swing.JLayeredPane();
        capaDefault = new javax.swing.JPanel();
        panelInfoDefault = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        textoGanador = new javax.swing.JTextField();
        panelDefault = new javax.swing.JPanel();
        capaMover = new javax.swing.JPanel();
        panelInfoMover = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        panelMover = new javax.swing.JPanel();
        capaAtacar = new javax.swing.JPanel();
        panelInfoAtacar = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TextoAtacar = new javax.swing.JTextArea();
        panelAtacar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaAtaque = new javax.swing.JList();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        bConfirmarAtaque = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        Icono = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        capaAsTactico = new javax.swing.JPanel();
        panelInfoAs = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        panelAs = new javax.swing.JPanel();
        capaReclutar = new javax.swing.JPanel();
        panelInfoReclutar = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TextoReclutar = new javax.swing.JTextArea();
        panelReclutar = new javax.swing.JPanel();
        bReclutarCachorro = new javax.swing.JButton();
        bReclutarAlumno = new javax.swing.JButton();
        bReclutarSuperior = new javax.swing.JButton();
        bReclutarAyudante = new javax.swing.JButton();
        bReclutarCoordinador = new javax.swing.JButton();
        bReclutarPame = new javax.swing.JButton();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        botonMover = new javax.swing.JToggleButton();
        botonAtacar = new javax.swing.JToggleButton();
        botonAsTactico = new javax.swing.JToggleButton();
        botonReclutar = new javax.swing.JToggleButton();
        botonFinalizarTurno = new javax.swing.JButton();
        botonRendirse = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DIInfWars - Enfrentamiento");
        setAlwaysOnTop(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setModalExclusionType(null);
        setPreferredSize(new java.awt.Dimension(800, 600));
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

        Info.setOpaque(true);
        Info.setPreferredSize(new java.awt.Dimension(660, 250));
        Info.setLayout(new javax.swing.OverlayLayout(Info));

        capaDefault.setLayout(new javax.swing.BoxLayout(capaDefault, javax.swing.BoxLayout.LINE_AXIS));

        panelInfoDefault.setBackground(new java.awt.Color(255, 255, 255));
        panelInfoDefault.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        panelInfoDefault.setAutoscrolls(true);
        panelInfoDefault.setMinimumSize(new java.awt.Dimension(320, 250));
        panelInfoDefault.setPreferredSize(new java.awt.Dimension(320, 250));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Información");

        textoGanador.setEditable(false);
        textoGanador.setBackground(new java.awt.Color(255, 255, 255));
        textoGanador.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textoGanador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textoGanador.setBorder(null);
        textoGanador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoGanadorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInfoDefaultLayout = new javax.swing.GroupLayout(panelInfoDefault);
        panelInfoDefault.setLayout(panelInfoDefaultLayout);
        panelInfoDefaultLayout.setHorizontalGroup(
            panelInfoDefaultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
            .addGroup(panelInfoDefaultLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textoGanador)
                .addContainerGap())
        );
        panelInfoDefaultLayout.setVerticalGroup(
            panelInfoDefaultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoDefaultLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(textoGanador, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        capaDefault.add(panelInfoDefault);

        panelDefault.setBackground(new java.awt.Color(255, 255, 255));
        panelDefault.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        panelDefault.setPreferredSize(new java.awt.Dimension(340, 250));

        javax.swing.GroupLayout panelDefaultLayout = new javax.swing.GroupLayout(panelDefault);
        panelDefault.setLayout(panelDefaultLayout);
        panelDefaultLayout.setHorizontalGroup(
            panelDefaultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 336, Short.MAX_VALUE)
        );
        panelDefaultLayout.setVerticalGroup(
            panelDefaultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 246, Short.MAX_VALUE)
        );

        capaDefault.add(panelDefault);

        Info.add(capaDefault);

        capaMover.setBackground(new java.awt.Color(255, 255, 255));
        capaMover.setPreferredSize(new java.awt.Dimension(660, 250));
        capaMover.setLayout(new javax.swing.BoxLayout(capaMover, javax.swing.BoxLayout.LINE_AXIS));

        panelInfoMover.setBackground(new java.awt.Color(255, 255, 255));
        panelInfoMover.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        panelInfoMover.setAutoscrolls(true);
        panelInfoMover.setMinimumSize(new java.awt.Dimension(320, 250));
        panelInfoMover.setPreferredSize(new java.awt.Dimension(320, 250));

        jLabel5.setText("Informacion");

        javax.swing.GroupLayout panelInfoMoverLayout = new javax.swing.GroupLayout(panelInfoMover);
        panelInfoMover.setLayout(panelInfoMoverLayout);
        panelInfoMoverLayout.setHorizontalGroup(
            panelInfoMoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoMoverLayout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(jLabel5)
                .addContainerGap(142, Short.MAX_VALUE))
        );
        panelInfoMoverLayout.setVerticalGroup(
            panelInfoMoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoMoverLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(221, Short.MAX_VALUE))
        );

        capaMover.add(panelInfoMover);

        panelMover.setBackground(new java.awt.Color(255, 255, 255));
        panelMover.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        panelMover.setPreferredSize(new java.awt.Dimension(340, 250));

        javax.swing.GroupLayout panelMoverLayout = new javax.swing.GroupLayout(panelMover);
        panelMover.setLayout(panelMoverLayout);
        panelMoverLayout.setHorizontalGroup(
            panelMoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 336, Short.MAX_VALUE)
        );
        panelMoverLayout.setVerticalGroup(
            panelMoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 246, Short.MAX_VALUE)
        );

        capaMover.add(panelMover);

        Info.add(capaMover);
        Info.setLayer(capaMover, 1);

        capaAtacar.setMinimumSize(new java.awt.Dimension(600, 250));
        capaAtacar.setName(""); // NOI18N
        capaAtacar.setPreferredSize(new java.awt.Dimension(600, 250));
        capaAtacar.setLayout(new javax.swing.BoxLayout(capaAtacar, javax.swing.BoxLayout.LINE_AXIS));

        panelInfoAtacar.setBackground(new java.awt.Color(255, 255, 255));
        panelInfoAtacar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        panelInfoAtacar.setAutoscrolls(true);
        panelInfoAtacar.setMinimumSize(new java.awt.Dimension(320, 250));
        panelInfoAtacar.setPreferredSize(new java.awt.Dimension(320, 250));

        jLabel24.setText("Informacion");

        TextoAtacar.setEditable(false);
        TextoAtacar.setColumns(20);
        TextoAtacar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TextoAtacar.setRows(5);
        jScrollPane2.setViewportView(TextoAtacar);

        javax.swing.GroupLayout panelInfoAtacarLayout = new javax.swing.GroupLayout(panelInfoAtacar);
        panelInfoAtacar.setLayout(panelInfoAtacarLayout);
        panelInfoAtacarLayout.setHorizontalGroup(
            panelInfoAtacarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoAtacarLayout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(jLabel24)
                .addContainerGap(138, Short.MAX_VALUE))
            .addGroup(panelInfoAtacarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        panelInfoAtacarLayout.setVerticalGroup(
            panelInfoAtacarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoAtacarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                .addContainerGap())
        );

        capaAtacar.add(panelInfoAtacar);

        panelAtacar.setBackground(new java.awt.Color(255, 255, 255));
        panelAtacar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        panelAtacar.setPreferredSize(new java.awt.Dimension(340, 250));

        listaAtaque.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Corto - 0 - 0", "Medio - 0 - 0", "Largo - 0 - 0" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listaAtaque.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(listaAtaque);

        jLabel28.setText("Elija Ataque:");

        jLabel29.setText("Rango - Daño - Golpes");

        bConfirmarAtaque.setText("Atacar!");

        jLabel30.setText("Objetivo");

        Icono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unidadPlaceholder.png"))); // NOI18N
        Icono.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel32.setText("Nivel");

        jLabel33.setText("HP");

        jLabel35.setText("1");

        jLabel36.setText("50");

        jLabel37.setForeground(new java.awt.Color(0, 0, 204));
        jLabel37.setText("35");

        jLabel34.setForeground(new java.awt.Color(255, 0, 0));
        jLabel34.setText("20");

        javax.swing.GroupLayout panelAtacarLayout = new javax.swing.GroupLayout(panelAtacar);
        panelAtacar.setLayout(panelAtacarLayout);
        panelAtacarLayout.setHorizontalGroup(
            panelAtacarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAtacarLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelAtacarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAtacarLayout.createSequentialGroup()
                        .addComponent(bConfirmarAtaque)
                        .addContainerGap())
                    .addGroup(panelAtacarLayout.createSequentialGroup()
                        .addGroup(panelAtacarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel28))
                        .addGroup(panelAtacarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAtacarLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel30)
                                .addGap(101, 101, 101))
                            .addGroup(panelAtacarLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(panelAtacarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Icono)
                                    .addGroup(panelAtacarLayout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addGroup(panelAtacarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel33)
                                            .addGroup(panelAtacarLayout.createSequentialGroup()
                                                .addComponent(jLabel32)
                                                .addGroup(panelAtacarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(panelAtacarLayout.createSequentialGroup()
                                                        .addGap(20, 20, 20)
                                                        .addComponent(jLabel36))
                                                    .addGroup(panelAtacarLayout.createSequentialGroup()
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jLabel35)))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel37)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel34)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        panelAtacarLayout.setVerticalGroup(
            panelAtacarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAtacarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAtacarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAtacarLayout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelAtacarLayout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Icono)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelAtacarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(jLabel35))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelAtacarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(jLabel36)
                            .addComponent(jLabel37)
                            .addComponent(jLabel34))))
                .addGap(51, 51, 51)
                .addComponent(bConfirmarAtaque)
                .addGap(43, 43, 43))
        );

        capaAtacar.add(panelAtacar);

        Info.add(capaAtacar);
        Info.setLayer(capaAtacar, 2);

        capaAsTactico.setLayout(new javax.swing.BoxLayout(capaAsTactico, javax.swing.BoxLayout.LINE_AXIS));

        panelInfoAs.setBackground(new java.awt.Color(255, 255, 255));
        panelInfoAs.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        panelInfoAs.setAutoscrolls(true);
        panelInfoAs.setMinimumSize(new java.awt.Dimension(320, 250));
        panelInfoAs.setPreferredSize(new java.awt.Dimension(320, 250));

        jLabel25.setText("Informacion");

        javax.swing.GroupLayout panelInfoAsLayout = new javax.swing.GroupLayout(panelInfoAs);
        panelInfoAs.setLayout(panelInfoAsLayout);
        panelInfoAsLayout.setHorizontalGroup(
            panelInfoAsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoAsLayout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jLabel25)
                .addContainerGap(144, Short.MAX_VALUE))
        );
        panelInfoAsLayout.setVerticalGroup(
            panelInfoAsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoAsLayout.createSequentialGroup()
                .addComponent(jLabel25)
                .addGap(0, 232, Short.MAX_VALUE))
        );

        capaAsTactico.add(panelInfoAs);

        panelAs.setBackground(new java.awt.Color(255, 255, 255));
        panelAs.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        panelAs.setPreferredSize(new java.awt.Dimension(340, 250));

        javax.swing.GroupLayout panelAsLayout = new javax.swing.GroupLayout(panelAs);
        panelAs.setLayout(panelAsLayout);
        panelAsLayout.setHorizontalGroup(
            panelAsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 336, Short.MAX_VALUE)
        );
        panelAsLayout.setVerticalGroup(
            panelAsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 246, Short.MAX_VALUE)
        );

        capaAsTactico.add(panelAs);

        Info.add(capaAsTactico);
        Info.setLayer(capaAsTactico, 3);

        capaReclutar.setLayout(new javax.swing.BoxLayout(capaReclutar, javax.swing.BoxLayout.LINE_AXIS));

        panelInfoReclutar.setBackground(new java.awt.Color(255, 255, 255));
        panelInfoReclutar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        panelInfoReclutar.setAutoscrolls(true);
        panelInfoReclutar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelInfoReclutar.setMinimumSize(new java.awt.Dimension(320, 250));
        panelInfoReclutar.setPreferredSize(new java.awt.Dimension(320, 250));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Información");

        jLabel26.setText("Su oro es:");

        jLabel27.setText("0");

        TextoReclutar.setColumns(20);
        TextoReclutar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TextoReclutar.setRows(5);
        jScrollPane3.setViewportView(TextoReclutar);

        javax.swing.GroupLayout panelInfoReclutarLayout = new javax.swing.GroupLayout(panelInfoReclutar);
        panelInfoReclutar.setLayout(panelInfoReclutarLayout);
        panelInfoReclutarLayout.setHorizontalGroup(
            panelInfoReclutarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
            .addGroup(panelInfoReclutarLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelInfoReclutarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        panelInfoReclutarLayout.setVerticalGroup(
            panelInfoReclutarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoReclutarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(panelInfoReclutarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addContainerGap())
        );

        capaReclutar.add(panelInfoReclutar);

        panelReclutar.setBackground(new java.awt.Color(255, 255, 255));
        panelReclutar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        panelReclutar.setAutoscrolls(true);
        panelReclutar.setPreferredSize(new java.awt.Dimension(340, 250));

        bReclutarCachorro.setText("Reclutar");
        bReclutarCachorro.setMargin(new java.awt.Insets(2, 0, 2, 0));

        bReclutarAlumno.setText("Reclutar");
        bReclutarAlumno.setMargin(new java.awt.Insets(2, 0, 2, 0));

        bReclutarSuperior.setText("Reclutar");
        bReclutarSuperior.setMargin(new java.awt.Insets(2, 0, 2, 0));

        bReclutarAyudante.setText("Reclutar");
        bReclutarAyudante.setMargin(new java.awt.Insets(2, 0, 2, 0));

        bReclutarCoordinador.setText("Reclutar");
        bReclutarCoordinador.setMargin(new java.awt.Insets(2, 0, 2, 0));

        bReclutarPame.setText("Reclutar");
        bReclutarPame.setMargin(new java.awt.Insets(2, 0, 2, 0));

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

        javax.swing.GroupLayout panelReclutarLayout = new javax.swing.GroupLayout(panelReclutar);
        panelReclutar.setLayout(panelReclutarLayout);
        panelReclutarLayout.setHorizontalGroup(
            panelReclutarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReclutarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelReclutarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelReclutarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addGroup(panelReclutarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelReclutarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bReclutarCachorro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bReclutarCoordinador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bReclutarAyudante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bReclutarSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bReclutarPame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bReclutarAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelReclutarLayout.setVerticalGroup(
            panelReclutarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReclutarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelReclutarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bReclutarCachorro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelReclutarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bReclutarAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelReclutarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bReclutarSuperior, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelReclutarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bReclutarAyudante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelReclutarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bReclutarCoordinador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelReclutarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bReclutarPame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        capaReclutar.add(panelReclutar);

        Info.add(capaReclutar);
        Info.setLayer(capaReclutar, 4);

        getContentPane().add(Info, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 315, -1, 250));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Turno de");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 110, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Jugador 1");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 40, 110, -1));

        botonMover.setText("Mover");
        botonMover.setMargin(new java.awt.Insets(2, 0, 2, 0));
        getContentPane().add(botonMover, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 70, 110, 70));

        botonAtacar.setText("Atacar");
        botonAtacar.setMargin(new java.awt.Insets(2, 0, 2, 0));
        getContentPane().add(botonAtacar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 150, 110, 70));

        botonAsTactico.setText("As Táctico");
        botonAsTactico.setMargin(new java.awt.Insets(2, 0, 2, 0));
        getContentPane().add(botonAsTactico, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 230, 110, 70));

        botonReclutar.setText("Reclutar");
        botonReclutar.setMargin(new java.awt.Insets(2, 0, 2, 0));
        getContentPane().add(botonReclutar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 320, 110, 70));

        botonFinalizarTurno.setText("Finalizar Turno");
        botonFinalizarTurno.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonFinalizarTurno.setMargin(new java.awt.Insets(2, 0, 2, 0));
        getContentPane().add(botonFinalizarTurno, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 410, 110, 120));

        botonRendirse.setText("Rendirse");
        botonRendirse.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonRendirse.setMargin(new java.awt.Insets(2, 0, 2, 0));
        getContentPane().add(botonRendirse, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 540, 110, -1));

        botonSalir.setText("Salir");
        getContentPane().add(botonSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 540, 110, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textoGanadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoGanadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoGanadorActionPerformed

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
    private javax.swing.JLabel Icono;
    private javax.swing.JLayeredPane Info;
    private javax.swing.JLayeredPane Tablero;
    private javax.swing.JTextArea TextoAtacar;
    private javax.swing.JTextArea TextoReclutar;
    private javax.swing.JButton bConfirmarAtaque;
    private javax.swing.JButton bReclutarAlumno;
    private javax.swing.JButton bReclutarAyudante;
    private javax.swing.JButton bReclutarCachorro;
    private javax.swing.JButton bReclutarCoordinador;
    private javax.swing.JButton bReclutarPame;
    private javax.swing.JButton bReclutarSuperior;
    private javax.swing.JToggleButton botonAsTactico;
    private javax.swing.JToggleButton botonAtacar;
    private javax.swing.JButton botonFinalizarTurno;
    private javax.swing.JToggleButton botonMover;
    private javax.swing.JToggleButton botonReclutar;
    private javax.swing.JButton botonRendirse;
    private javax.swing.JButton botonSalir;
    private javax.swing.JPanel capaAsTactico;
    private javax.swing.JPanel capaAtacar;
    private javax.swing.JPanel capaDefault;
    private javax.swing.JPanel capaMapa;
    private javax.swing.JPanel capaMover;
    private javax.swing.JPanel capaRango;
    private javax.swing.JPanel capaReclutar;
    private javax.swing.JPanel capaUnidad;
    private javax.swing.JFrame jFrame1;
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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList listaAtaque;
    private javax.swing.JPanel panelAs;
    private javax.swing.JPanel panelAtacar;
    private javax.swing.JPanel panelDefault;
    private javax.swing.JPanel panelInfoAs;
    private javax.swing.JPanel panelInfoAtacar;
    private javax.swing.JPanel panelInfoDefault;
    private javax.swing.JPanel panelInfoMover;
    private javax.swing.JPanel panelInfoReclutar;
    private javax.swing.JPanel panelMover;
    private javax.swing.JPanel panelReclutar;
    private javax.swing.JTextField textoGanador;
    // End of variables declaration//GEN-END:variables

    
}
