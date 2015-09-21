/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diinfwars.Controllers;

import diinfwars.Models.Batalla;
import diinfwars.Models.Edificio;
import diinfwars.Models.Estratega;
import diinfwars.Models.Jugador;
import diinfwars.Models.Mapa;
import diinfwars.Models.Registro;
import diinfwars.Models.Unidad;
import diinfwars.Models.Unidades.CoordinadorAyudantes;
import diinfwars.Models.Unidades.Pame;
import diinfwars.Views.VEnfrentamiento;
import diinfwars.Views.VPreJugar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Controlador de la vista enfrentamiento
 * @author MonodetH
 */
public class CEnfrentamiento implements ActionListener,MouseListener,ListSelectionListener{
    /**Controlador padre*/
    private CPreJugar pJugar;
    private CPreTorneo pTorneo;
    private Registro r;
    /**Instancia de la vista asociada*/
    private VEnfrentamiento v;
    /**Jugador asociado*/
    private Jugador jugador1,jugador2;
    /**Estrategas por jugador*/
    private Estratega estratega1,estratega2;
    /**Mapa en el que se hará la batalla*/
    private Mapa mapa;
    /**Datos de la batalla*/
    private Batalla batalla;
       
    private ArrayList<Unidad> uMovidas = new ArrayList<Unidad>();
    private ArrayList<Unidad> uAtacantes = new ArrayList<Unidad>();
    
    private Unidad unidadAtacante = null;
    private Unidad unidadDefensora = null;
    
    private Random rand = new Random();
    
    
    
    /**Constructor que instancia la vista*/
    public CEnfrentamiento(CPreJugar padre, int valorMapa,int oroInicio, int oroInicioKiosco, String Jugador1, String Jugador2){
        /*Esto se hace en CPreEnfrentamiento*/
        Jugador jug1=new Jugador(Jugador1), jug2 = new Jugador(Jugador2);
        Mapa mapa1 = new Mapa(valorMapa);
        /*El objeto Batalla deberia ser pasado al constructor por CPreEnfrentamiento*/
        Batalla datosBatalla = new Batalla(mapa1,oroInicioKiosco,jug1,oroInicio,1,2,3,"Estudioso","Deportista",jug2,oroInicio,1,3,2,"Estudioso","Deportista");

        /*Aqui empieza este controlador*/
        this.pJugar = padre;
        this.batalla = datosBatalla;
        this.mapa = batalla.getMapa();
        this.jugador1 = batalla.getJugador1();
        this.jugador2 = batalla.getJugador2();
        this.estratega1 = batalla.getEstratega1();
        this.estratega2 = batalla.getEstratega2();
    }
    
    public CEnfrentamiento(CPreJugar padre,Batalla datosBatalla){
        /*Constructor que instancia la vista*/
        this.pJugar = padre;
        this.batalla = datosBatalla;
        this.mapa = batalla.getMapa();
        this.jugador1 = batalla.getJugador1();
        this.jugador2 = batalla.getJugador2();
        this.estratega1 = batalla.getEstratega1();
        this.estratega2 = batalla.getEstratega2();
    }
    /** Sobrecarga del constructor para usar torneo como padre*/
    public CEnfrentamiento(CPreTorneo padre,Batalla datosBatalla){
        /*Constructor que instancia la vista*/
        this.pTorneo = padre;
        this.batalla = datosBatalla;
        this.mapa = batalla.getMapa();
        this.jugador1 = batalla.getJugador1();
        this.jugador2 = batalla.getJugador2();
        this.estratega1 = batalla.getEstratega1();
        this.estratega2 = batalla.getEstratega2();
    }
    
    public void run(){
        if (this.v == null){
            this.v = new VEnfrentamiento(this,this,this);
            v.setLabel2(jugador1.getNombre());
            v.dibujarTerreno(mapa.terrenoToString());
            v.actualizarTerrenoToolTip(mapa.terrenoToolTip(jugador1.getNombre(),jugador2.getNombre()));
            v.dibujarUnidades(mapa.unidadesToString());
            }
        this.v.setVisible(true);
        this.v.getBotonSalir().setVisible(false);
        this.v.getTextoGanador2().setVisible(false);
        try {
            this.rutinaNuevoTurno();
        } catch (IOException ex) {
            Logger.getLogger(CEnfrentamiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void rutinaNuevoTurno() throws IOException{
        String nombreJugador = (v.getJugador()==1)?jugador2.getNombre():jugador1.getNombre();
        v.toggleJugador(nombreJugador);
        v.setModo(0);
        v.dibujarRango(new boolean[9][20]);
        
        // se reinician las listas de movidos y atacantes
        uMovidas.clear();
        uAtacantes.clear();
        this.unidadAtacante = null;
        this.unidadDefensora = null;
        
        Estratega estratega = (v.getJugador() == 1)?estratega1:estratega2;
        
        //Helear
        this.mapa.curaPorPame(v.getJugador());
        this.mapa.curaPorEdificio(v.getJugador());
        estratega.curaPorMovimiento();
        estratega.resetearMovimiento();
        
        
        //Otorgar oro Kioscos
        Iterator<Edificio> iterador = mapa.getEdificios().iterator();
        while(iterador.hasNext()){
            Edificio edif = iterador.next();
            if(edif.getTipo() == "Kiosco" && edif.getDueno()== v.getJugador()){
                estratega.otorgarOro(batalla.getOroKiosco());
                // IMPRIMO
                this.v.getTextoReclutar().append("Jugador "+String.valueOf(v.getJugador())+" gana "+String.valueOf(batalla.getOroKiosco())+" oro.");
                this.v.getTextoReclutar().append(System.getProperty("line.separator"));
                this.v.getTextoReclutar().append(System.getProperty("line.separator"));


                System.out.println("Jugador "+String.valueOf(v.getJugador())+" gana "+String.valueOf(batalla.getOroKiosco())+" oro.");
            }
        }

        //Rutina de descuentos
        estratega.cobrarMantencion();
        
        estratega.restarTurnoAS();//Cooldown As tactico
        estratega.restarTurnoMod();//Modificadores
        ArrayList<Unidad> eliminados =  estratega.restarTurnoMuertos();//Restar turno muerte y eliminar a los muertos del estratega
        this.mapa.eliminarMuertos(eliminados);
        
        //Actualiza los paneles de informacion
        v.actualizarPanelReclutar(estratega.getOro());
        
        //v.actualizarPanelDefault(estratega.cobrarMantencion());

        v.dibujarUnidades(mapa.unidadesToString());
        v.actualizarTerrenoToolTip(mapa.terrenoToolTip(jugador1.getNombre(),jugador2.getNombre()));
        v.activarAcciones();
        if(v.getJugador()==1 && jugador1.isCpu()){
            v.desactivarAcciones();
            this.rutinaIA(1);
        }else if(v.getJugador()==2 && jugador2.isCpu()){
            v.desactivarAcciones();
            this.rutinaIA(2);
        }
        
    }
    
    
    
    public void finPartida(String ganador,String perdedor) throws IOException{
        Registro registro = new Registro(ganador, perdedor);
        v.setModo(0);
        this.v.getBotonAtacar().setEnabled(false);
        this.v.getBotonMover().setEnabled(false);
        this.v.getBotonReclutar().setEnabled(false);
        this.v.getBotonAsTactico().setEnabled(false);
        this.v.getBotonFinalizarTurno().setEnabled(false);
        this.v.getBotonRendirse().setEnabled(false);
        this.v.getBotonSalir().setVisible(true);
        this.v.getTextoGanador2().setVisible(true);
        this.v.getBotonRendirse().setVisible(false);

        
        this.v.getTextoGanador2().setText("GANADOR: "+(ganador));

        System.out.println("HA ACABADO LA PARTIDA, EL GANADOR ES "+(ganador));
    }
    
    protected void rutinaIA(int equipo) throws IOException{
        Estratega estratega = (equipo == 1)?estratega1:estratega2;
        Estratega estrategaEnemigo = (equipo == 2)?estratega1:estratega2;
        // Casos particulares
        //Profesor
        uMovidas.add(estratega.getProfesor());
        //Coordinador de ayudantes
        ArrayList<Unidad> unidades = estratega.getUnidades();
        Iterator<Unidad> iterador = unidades.iterator();
        while(iterador.hasNext()){
            Unidad coordinador = iterador.next();
            if (coordinador instanceof CoordinadorAyudantes){
                int[] pos = mapa.getPos(coordinador),destino=new int[2];
                int col = (equipo == 1)?1:18;
                if(pos[1]!=col && !uMovidas.contains(coordinador) && !coordinador.isDead()){
                    destino[1]=col;
                    if(mapa.getUnidad(4, col)==null && mapa.getCasilla(4,col).isHabilitada() ){
                        destino[0]=4;
                        if(mapa.moverHacia(pos,destino,true)){
                            uMovidas.add(coordinador);
                            coordinador.setInmovil(false);
                            v.dibujarUnidades(mapa.unidadesToString());
                        }
                    }else if(mapa.getUnidad(3, col)==null && mapa.getCasilla(3,col).isHabilitada()){
                        destino[0]=3;
                        if(mapa.moverHacia(pos,destino,true)){
                            uMovidas.add(coordinador);
                            coordinador.setInmovil(false);
                            v.dibujarUnidades(mapa.unidadesToString());
                        }
                    }else if(mapa.getUnidad(5, col)==null && mapa.getCasilla(5,col).isHabilitada()){
                        destino[0]=5;
                        if(mapa.moverHacia(pos,destino,true)){
                            uMovidas.add(coordinador);
                            coordinador.setInmovil(false);
                            v.dibujarUnidades(mapa.unidadesToString());
                        }
                    }else if(mapa.getUnidad(2, col)==null && mapa.getCasilla(2,col).isHabilitada()){
                        destino[0]=2;
                        if(mapa.moverHacia(pos,destino,true)){
                            uMovidas.add(coordinador);
                            coordinador.setInmovil(false);
                            v.dibujarUnidades(mapa.unidadesToString());
                        }
                    }else if(mapa.getUnidad(6, col)==null && mapa.getCasilla(6,col).isHabilitada()){
                        destino[0]=6;
                        if(mapa.moverHacia(pos,destino,true)){
                            uMovidas.add(coordinador);
                            coordinador.setInmovil(false);
                            v.dibujarUnidades(mapa.unidadesToString());
                        }
                    }else if(mapa.getUnidad(1, col)==null && mapa.getCasilla(1,col).isHabilitada()){
                        destino[0]=1;
                        if(mapa.moverHacia(pos,destino,true)){
                            uMovidas.add(coordinador);
                            coordinador.setInmovil(false);
                            v.dibujarUnidades(mapa.unidadesToString());
                        }
                    }else if(mapa.getUnidad(7, col)==null && mapa.getCasilla(7,col).isHabilitada()){
                        destino[0]=7;
                        if(mapa.moverHacia(pos,destino,true)){
                            uMovidas.add(coordinador);
                            coordinador.setInmovil(false);
                            v.dibujarUnidades(mapa.unidadesToString());
                        }
                    }
                }else{
                    if(!uMovidas.contains(coordinador)){uMovidas.add(coordinador);} //Para que no se mueva hacia el oponente
                }
            }
        }
        //Pame
        iterador = unidades.iterator();
        while(iterador.hasNext()){
            Unidad pame = iterador.next();
            if (pame instanceof Pame && !uMovidas.contains(pame)&&!pame.isDead()){
                Iterator<Unidad> iterador2 = unidades.iterator();
                Unidad danada = null;
                while(iterador2.hasNext()){
                    Unidad unidad = iterador2.next();
                    if(danada == null || (danada.getDano() < unidad.getDano() && !mapa.tienePame(unidad))){
                        danada = unidad;
                    }
                }
                if(danada.getDano()!=0){
                    if(mapa.moverHacia(mapa.getPos(pame),mapa.getPos(danada),false)){
                        uMovidas.add(pame);
                        pame.setInmovil(false);
                        v.dibujarUnidades(mapa.unidadesToString());
                    }
                }
                
            }
        }
        
        
        // Comportamiento general
        //Atacar
        iterador = unidades.iterator();
        while(iterador.hasNext()){
            Unidad unidad = iterador.next();
            if(!uAtacantes.contains(unidad) && !unidad.isDead()){
                Unidad objetivo = mapa.getObjetivo(unidad);
                int[] ataque = mapa.mejorAtaque(unidad,objetivo);
                if(objetivo!=null){
                    if(this.atacar(unidad, objetivo, ataque)){
                        uAtacantes.add(unidad);
                    }
                }
            }
        }
        
        //Mover
        iterador = unidades.iterator();
        while(iterador.hasNext()){
            Unidad unidad = iterador.next();
            if(!uMovidas.contains(unidad) && !unidad.isDead()){
                if(unidad.porcentajeVida() >= 25){
                    if(mapa.moverHacia(mapa.getPos(unidad),mapa.getPos(estrategaEnemigo.getProfesor()),false)){       
                        uMovidas.add(unidad);
                        unidad.setInmovil(false);
                        v.dibujarUnidades(mapa.unidadesToString());
                    }
                }else{
                    if(mapa.moverHacia(mapa.getPos(unidad),mapa.getPos(estratega.getProfesor()),false)){       
                        uMovidas.add(unidad);
                        unidad.setInmovil(false);
                        v.dibujarUnidades(mapa.unidadesToString());
                    }
                }
            }
        }
        
        //Intentar atacar de nuevo
        iterador = unidades.iterator();
        while(iterador.hasNext()){
            Unidad unidad = iterador.next();
            if(!uAtacantes.contains(unidad) && !unidad.isDead()){
                Unidad objetivo = mapa.getObjetivo(unidad);
                int[] ataque = mapa.mejorAtaque(unidad,objetivo);
                if(objetivo!=null){
                    if(this.atacar(unidad, objetivo, ataque)){
                        uAtacantes.add(unidad);
                    }
                }
            }
        }
        
        // Reclutar
        while(estratega.getOro()>=4 && mapa.getPosReclutar(equipo)!=null){
            int[] pos = mapa.getPosReclutar(equipo);
            int oro = estratega.getOro();
            
            String tipoUnidad = "Cachorro";
            if(oro >= 12 && estratega.porcentajePame()<20){tipoUnidad = "Pame";}
            else if(oro>=10 && estratega.cantidadCoordinador()<3){tipoUnidad = "CoordinadorAyudantes";}
            else if(oro>=8){tipoUnidad = (rand.nextBoolean())?"Ayudante":"AlumnoNivelSuperior";}
            else if(oro>=6){tipoUnidad = "Alumno";}
            
            Unidad unidad = estratega.reclutar(tipoUnidad);
            mapa.ubicarUnidad(unidad,pos[0], pos[1]);
            uMovidas.add(unidad);
            uAtacantes.add(unidad);

            v.dibujarUnidades(mapa.unidadesToString());
            v.actualizarPanelReclutar(estratega.getOro());
            
        }
    }
    
    public boolean atacar(Unidad atacante, Unidad defensora,int[] ataque) throws IOException{
        if(atacante != null && defensora != null && !atacante.isDead() && !defensora.isDead() && !uAtacantes.contains(atacante) && atacante.getEquipo() != defensora.getEquipo()){
            // OBTENER ATAQUES
            int[] contra = defensora.getAtaque(ataque[0]); // Mismo rango que atacante
            if(contra == null){contra = defensora.getAtaque(1);} // pedir ataque corto
            if(contra == null){contra = defensora.getAtaque(2);} // pedir ataque medio
            if(contra == null){contra = defensora.getAtaque(3);} // pedir ataque largo
            if(contra == null){contra = new int[3];contra[0]=1;contra[1]=0;contra[2]=0;} // ataque nulo

            // Atacar
            int golpesA = ataque[2], golpesD = contra[2]; // numero de golpes
            int danoA = 0, danoD = 0; // daño hecho
            int missA = 0; // cantidad de fallas
            int critMissA = 0; // cantidad de fallas criticas
            int danoCritMissA = 0;
            int[] posA = mapa.getPos(atacante), posD = mapa.getPos(defensora);
            int defensaA = mapa.getDefensa(posA[0],posA[1]), defensaD = mapa.getDefensa(posD[0],posD[1]); // Defensa de la unidad en su casilla
            while((golpesA != 0 || golpesD != 0) && (!atacante.isDead() && !defensora.isDead())){
                //ATAQUE
                if(golpesA > 0){
                    if(rand.nextInt(100) >= defensaD){//ATACAR
                        danoA += ataque[1];
                        atacante.otorgarExp(ataque[1]);
                        if(defensora.recibirDano(ataque[1])){
                            atacante.otorgarExp(defensora.getExpMuerte());
                        }
                    }else if (rand.nextInt(100) < atacante.getCritMiss()){// Critical miss
                        danoCritMissA += (ataque[1]+1)/2;
                        critMissA++;
                        atacante.recibirDano((ataque[1]+1)/2);
                    }else{missA++;}// miss
                    golpesA--;


                }
                //CONTRAATAQUE
                if(golpesD >0 && !atacante.isDead() && !defensora.isDead()){
                    if(rand.nextInt(100) >= defensaA){
                        danoD += contra[1];
                        defensora.otorgarExp(contra[1]);
                        if(atacante.recibirDano(contra[1])){
                            defensora.otorgarExp(atacante.getExpMuerte());
                        }
                    }
                    golpesD--;
                } 
            }
            // Imprimir resultado por pantalla

            this.v.getTextoAtacar().append("La unidad ha hecho ");
            this.v.getTextoAtacar().append(String.valueOf(danoA));
            this.v.getTextoAtacar().append(" de daño, fallando ");
            this.v.getTextoAtacar().append(String.valueOf(missA));
            this.v.getTextoAtacar().append(" golpes de ");
            this.v.getTextoAtacar().append(String.valueOf(ataque[2] - golpesA));
            this.v.getTextoAtacar().append(System.getProperty("line.separator"));

            this.v.getTextoAtacar().append("Se han producido ");
            this.v.getTextoAtacar().append(String.valueOf(critMissA));
            this.v.getTextoAtacar().append(" fallas criticas, autoinflingiendose ");
            this.v.getTextoAtacar().append(String.valueOf(danoCritMissA));
            this.v.getTextoAtacar().append(" de daño.");
            this.v.getTextoAtacar().append(System.getProperty("line.separator"));

            this.v.getTextoAtacar().append("El oponente hizo ");
            this.v.getTextoAtacar().append(String.valueOf(danoD));
            this.v.getTextoAtacar().append(" de daño como contraataque.");
            this.v.getTextoAtacar().append(System.getProperty("line.separator"));
            this.v.getTextoAtacar().append(System.getProperty("line.separator"));

            this.v.gettextoGanador().append("La unidad ha hecho ");
            this.v.gettextoGanador().append(String.valueOf(danoA));
            this.v.gettextoGanador().append(" de daño, fallando ");
            this.v.gettextoGanador().append(String.valueOf(missA));
            this.v.gettextoGanador().append(" golpes de ");
            this.v.gettextoGanador().append(String.valueOf(ataque[2] - golpesA));
            this.v.gettextoGanador().append(System.getProperty("line.separator"));

            this.v.gettextoGanador().append("Se han producido ");
            this.v.gettextoGanador().append(String.valueOf(critMissA));
            this.v.gettextoGanador().append(" fallas criticas, autoinflingiendose ");
            this.v.gettextoGanador().append(String.valueOf(danoCritMissA));
            this.v.gettextoGanador().append(" de daño.");
            this.v.gettextoGanador().append(System.getProperty("line.separator"));

            this.v.gettextoGanador().append("El oponente hizo ");
            this.v.gettextoGanador().append(String.valueOf(danoD));
            this.v.gettextoGanador().append(" de daño como contraataque.");
            this.v.gettextoGanador().append(System.getProperty("line.separator"));
            this.v.gettextoGanador().append(System.getProperty("line.separator"));
            
            System.out.print("La unidad ha hecho ");
            System.out.print(danoA);
            System.out.print(" de daño, fallando ");
            System.out.print(missA);
            System.out.print(" golpes de ");
            System.out.println(ataque[2] - golpesA);
            System.out.print("Se han producido ");
            System.out.print(critMissA);
            System.out.print(" fallas criticas, autoinflingiendose ");
            System.out.print(danoCritMissA);
            System.out.println(" de daño ");
            System.out.print("El oponente hizo ");
            System.out.print(danoD);
            System.out.println(" de daño como contraataque");
            
            
            uAtacantes.add(atacante);
        }
        
        v.dibujarUnidades(mapa.unidadesToString());
        v.setEnableAtacar(false);

        //TERMINO DE LA PARTIDA
        if(estratega1.getProfesor().isDead()){
            finPartida(jugador2.getNombre(),jugador1.getNombre());
            return true;
        }
        if(estratega2.getProfesor().isDead()){
            finPartida(jugador1.getNombre(),jugador2.getNombre());
            return true;
        }
        return false;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        // En caso de que sean botones
        if( source instanceof JButton){
            JButton boton = (JButton) e.getSource();
            String nombreJugador = (v.getJugador()==1)?jugador2.getNombre():jugador1.getNombre();      
            
            //Salir
            if(boton.getText()== "Salir"){
                v.setVisible(false);
                v.dispose();
                pJugar.run();
            }

            //Rendirse
            if(boton.getText() == "Rendirse"){
                if (nombreJugador == jugador2.getNombre()){
                    try {
                        finPartida(jugador2.getNombre(),jugador1.getNombre());
                    } catch (IOException ex) {
                        Logger.getLogger(CEnfrentamiento.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }           
                else if (nombreJugador == jugador1.getNombre()){
                    try {
                        finPartida(jugador1.getNombre(),jugador2.getNombre());
                    } catch (IOException ex) {
                        Logger.getLogger(CEnfrentamiento.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            // finalizar turno
            if(boton.getText() == "Finalizar Turno"){
                try {
                    this.rutinaNuevoTurno();
                } catch (IOException ex) {
                    Logger.getLogger(CEnfrentamiento.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            else if(boton.getParent() == v.getPanelReclutar()){
                String tipoUnidad = "Cachorro";
                if(boton == v.getReclutarCachorro()){tipoUnidad = "Cachorro";}
                else if(boton == v.getReclutarAlumno()){tipoUnidad = "Alumno";}
                else if(boton == v.getReclutarSuperior()){tipoUnidad = "AlumnoNivelSuperior";}
                else if(boton == v.getReclutarAyudante()){tipoUnidad = "Ayudante";}
                else if(boton == v.getReclutarCoordinador()){tipoUnidad = "CoordinadorAyudantes";}
                else if(boton == v.getReclutarPame()){tipoUnidad = "Pame";}
                
                int[] pos = v.getCasillaSeleccionada();
                if(v.getModo() == 4 && v.enRango(pos[0], pos[1])){
                    Estratega estratega = (v.getJugador() == 1)?estratega1:estratega2;
                    Unidad unidad = estratega.reclutar(tipoUnidad);
                    mapa.ubicarUnidad(unidad,pos[0], pos[1]);
                    uMovidas.add(unidad);
                    uAtacantes.add(unidad);
                    
                    boolean[][] rango = mapa.getRango(v.getModo(),pos[0],pos[1],v.getJugador(),v.getRangoAtaque());
                    v.dibujarRango(rango);
                    v.dibujarUnidades(mapa.unidadesToString());
                    v.actualizarPanelReclutar(estratega.getOro());
                }
            }
            
            else if(boton == v.getBConfirmarAtaque()){
                try {
                    this.atacar(this.unidadAtacante,this.unidadDefensora,v.getAtaqueSeleccionado());
                } catch (IOException ex) {
                    Logger.getLogger(CEnfrentamiento.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
            
        // En caso de que sean botones toggle (los modos del tablero).
        else if(source instanceof JToggleButton){
            JToggleButton boton = (JToggleButton) e.getSource();
            
            // Dejar en estado neutro
            this.unidadAtacante = null;
            this.unidadDefensora = null;
            this.v.setEnableAtacar(false);
            
            // cambio de modo
            if(boton.getText() == "Mover"){
                v.setModo(1);
            }
            else if(boton.getText() == "Atacar"){
                v.setModo(2);
                int[] pos = v.getCasillaSeleccionada();
                Unidad unidad = mapa.getUnidad(pos[0], pos[1]);
                if(unidad != null){
                    String[] listaAtaques = unidad.getListaAtaques();
                    v.setListaAtaques(listaAtaques);
                }
                
            }
            else if(boton.getText() == "As Táctico"){
                v.setModo(3);
            }
            else if(boton.getText() == "Reclutar"){
                v.setModo(4);
                Estratega estratega = (v.getJugador() == 1)?estratega1:estratega2;
                v.actualizarPanelReclutar(estratega.getOro());
                
            }
            int[] casillaActual = v.getCasillaSeleccionada();
            boolean[][] rango = mapa.getRango(v.getModo(),casillaActual[0],casillaActual[1],v.getJugador(),v.getRangoAtaque());
            v.dibujarRango(rango);
            
        // En caso de que sea una accion no implementada
        }else{
            System.out.println("Otro");
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        JLabel source = (JLabel) e.getSource();
        
        // Obtener posicion seleccionada
        int i=0, j=0;
        for(int x=0;x<9;x++){
            for(int y=0;y<20;y++){
                if(source.equals(v.getMatrizUnidad()[x][y])){
                    i=x;
                    j=y;
                }
            }
        }
        
        // Obtener informacion necesaria
        int[] posAntigua = v.getCasillaSeleccionada(); //Posicion anterior a la ultima seleccion
            // i, j posicion actual
        Unidad uAntigua = mapa.getUnidad(posAntigua[0], posAntigua[1]); //unidad en la posicion antigua, si la hay
        Unidad uNueva = mapa.getUnidad(i,j); //unidad en la posicion nueva, si la hay
        
        // Procedimiento general pre analisis
        if(uNueva != null){
            if(v.getModo() != 2 && uNueva!=uAntigua){
                String[] listaAtaques = uNueva.getListaAtaques();
                v.setListaAtaques(listaAtaques);
            }
        }else{
            v.setListaAtaques(null);
        }
        if(uNueva != null){
            String sprite = uNueva.getSprite();
            String lvl = String.valueOf(uNueva.getNivel());
            String hpActual = String.valueOf(uNueva.getHp());
            this.v.setInfoAtacante(sprite,lvl,hpActual);
        }
        
        
        
        // Analizar segun modo
        if(v.getModo() == 1){ // modo mover
            if(uAntigua != null && v.enRango(i, j) && v.getJugador() == uAntigua.getEquipo() && !uMovidas.contains(uAntigua) && !uAntigua.isDead()){
                if (mapa.moverUnidad(posAntigua[0], posAntigua[1], i, j)){
                    uMovidas.add(uAntigua);
                    uAntigua.setInmovil(false);
                }
                v.dibujarUnidades(mapa.unidadesToString());
                v.actualizarTerrenoToolTip(mapa.terrenoToolTip(jugador1.getNombre(),jugador2.getNombre()));
            }
            this.v.clearCasillaObjetivo();
            this.v.setCasillaSeleccionada(i,j);
            
            
            
        }else if(v.getModo() == 2){ // modo atacar
            this.unidadAtacante = null;
            this.unidadDefensora = null;
            this.v.setEnableAtacar(false);
            
            //Atacante y defensor valido
            if(uAntigua != null && !uAtacantes.contains(uAntigua) && v.getJugador() == uAntigua.getEquipo() && v.enRango(i, j)
                && uNueva != null  &&  uAntigua.getEquipo() != uNueva.getEquipo() 
                && i != v.getCasillaObjetivo()[0] && j != v.getCasillaObjetivo()[1]){
                    this.v.getTextoAtacar().setText("");
                    String sprite = uAntigua.getSprite();
                    String lvl = String.valueOf(uAntigua.getNivel());
                    String hpActual = String.valueOf(uAntigua.getHp());
                    this.v.setInfoAtacante(sprite,lvl,hpActual);
                    //this.v.setInfoDefensor();
                
                    System.out.println("SE DEBE MOSTRAR LA INFO EN EL PANEL");
                    this.unidadAtacante = uAntigua;
                    this.unidadDefensora = uNueva;
                    
                    this.v.setEnableAtacar(true);
                    this.v.setCasillaObjetivo(i,j);
            }else{
                if(uNueva != null){
                    if(uNueva!=uAntigua){
                        String[] listaAtaques = uNueva.getListaAtaques();
                        v.setListaAtaques(listaAtaques);
                    }
                }
                this.v.clearCasillaObjetivo();
                this.v.setCasillaSeleccionada(i,j);
            }
            
            
        }else if(v.getModo() == 3){ // modo as tactico
            // aqui depende de cada as
            this.v.setCasillaSeleccionada(i,j);
        }else if(v.getModo() == 4){ // modo reclutar
            // CREO QUE NO SE HACE NADA AQUI
            this.v.setCasillaSeleccionada(i,j);
        }else{ // Modo 0
            this.v.setCasillaSeleccionada(i,j);
        }
        
        // Dibujar nuevo rango
        i = v.getCasillaSeleccionada()[0];
        j = v.getCasillaSeleccionada()[1];
        v.dibujarRango(mapa.getRango(v.getModo(), i, j,v.getJugador(),v.getRangoAtaque()));
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int[] casillaActual = v.getCasillaSeleccionada();
        boolean[][] rango = mapa.getRango(v.getModo(),casillaActual[0],casillaActual[1],v.getJugador(),v.getRangoAtaque());
        v.dibujarRango(rango);
    }
    


    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}}
    
//    public static void main(String[] args){
//        new CEnfrentamiento(null, v.valorMapa);
//    }

    

