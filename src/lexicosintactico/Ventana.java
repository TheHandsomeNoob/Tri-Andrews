/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicosintactico;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luis Fer
 */
public class Ventana extends javax.swing.JFrame {
 FileNameExtensionFilter filtro= new FileNameExtensionFilter("Archivos Word y txt","docx","txt");
    File f;
    JFileChooser j= new JFileChooser();
    String data1 [][]={};
   String cabecera1[]={"No."," Token "," Tipo"};
    String path;
    int cont=0;
    int errores;
    String mensajini="";
    String tipo="";
    
        private void cmasmas() {
        errores=0;
        LinkedList <String> ENT = new LinkedList<>();
        LinkedList <String> DEC = new LinkedList<>();
        LinkedList <String> TEXT = new LinkedList<>();
        LinkedList <String> TAKE = new LinkedList<>();
        
        String
                simbolo = "([=])",
                
                id = "([(a-z)(A-Z)](\\w)*)",
                num = "((\\d)+)",
                dec = "((\\d)+(\\.)(\\d)+)",
                text = "((((#)[.\\W\\w\\s]*(#))|("+id+"))((\\s)*(\\+)((\\s)*((#)[.\\W\\w\\s]*(#))|("+id+")))*)",
                send = "((\\s)*COUT(\\s)*(\\()(\\s)*(\\s)*>(\\s)*(\\s)*>(\\s)*(((#)[.\\W\\w\\s]*(#))|("+id+"))((\\s)*(\\+)((\\s)*((#)[.\\W\\w\\s]*(#))|("+id+")))*(\\s)*>(\\s)*(\\s)*>(\\s)*(\\s)*(\\))(\\s)*(;))",
                take = "((\\s)*CIN(\\s)*(\\()(\\s)*(<(\\s)*<(\\s)(((#)[.\\W\\w\\s]*(#))|("+id+"))((\\s)*(\\+)((\\s)*((#)[.\\W\\w\\s]*(#))|("+id+")))*<(\\s)*<(\\s)(\\s)*(\\))(\\s)*(;))",
                operaciones = "(("+id+"|"+num+"|"+dec+")(\\s)*([+-/*](\\s)*("+id+"|"+num+"|"+dec+"))+)",
                defVal = "((\\s)*"+id+"(\\s)*=(\\s)*("+id+"|"+text+"|"+operaciones+"|"+num+"|"+dec+")(\\s)*(;))",
                defValVar = "((\\s)*"+id+"(\\s)*=(\\s)*("+id+"|"+text+"|"+operaciones+"|"+num+"|"+dec+")(\\s)*)",
                condicion = id+"(\\s)*"+simbolo+"(\\s)*("+id+"|"+num+"|"+dec+")((\\s)*([(&&)(||)](\\s)*"+id+"(\\s)*"+simbolo+"(\\s)*("+id+"|"+num+"|"+dec+")))*",
                var = "((\\s)*DIM (\\s)*("+id+"|"+defValVar+")((\\s)*(,(\\s)*("+id+"|"+defValVar+")))*(\\s)*(AS)(\\s)*((INTEGER)|(DOUBLE)|(STRING))(\\b)(\\s)*(;))",

                 entero = "[0-9]*",
                decimal = "[0-9]*.[0-9]+";
        
                
                LinkedList <Integer> error = new LinkedList<>();
                StringTokenizer st = new StringTokenizer(txtATexto1.getText(),";{}",true);
                String token = "", txt = "", e;
                int i = 1, mainE = 0, start = 0, when = 0, it = 0, eB = 0;
                Error.setText("");
                
                if(!txtATexto1.getText().equals("")) {
                    
                    while (st.hasMoreTokens()){
                        token = st.nextToken();
                        if(st.hasMoreTokens())token = token+st.nextToken();
                        if(token.matches("[.\\W\\w\\s]*(\\})") && st.countTokens() == 1){
                            String auxTok = st.nextToken();
                            token = token+(auxTok.substring(0,auxTok.indexOf("\n")));
                        }
                            StringTokenizer lin = new StringTokenizer(token,"\n",true);
                            while (lin.hasMoreTokens()){
                                e = lin.nextToken();
                                if("\n".equals(e)) i++;
                            }
                            if((st.hasMoreTokens() == false && (start > 0 || when > 0 || it > 0)) || (start < 0 || when < 0 || it < 0)) eB = 1;
                            
                            if((token.matches(send) || token.matches(take) || token.matches(var) || token.matches(defVal) ||token.matches("(\\s)*(\\$)")) && eB == 0){
                               if(token.matches(take)){
                                   
                               }
                                if(token.matches(var)){
                                    StringTokenizer stTipo = new StringTokenizer(token," ,;");
                                    String tipo = stTipo.nextToken();
                                    
                                    if(tipo.contains("INTEGER")){
                                        
                                        while(stTipo.hasMoreTokens()){
                                            tipo = stTipo.nextToken();
                                            
                                            if(ENT.contains(tipo) || DEC.contains(tipo) || TEXT.contains(tipo)|| TAKE.contains(tipo)){
                                                Error.setText("La Variable esta repetida ("+tipo+") "+i+": \n"
                                                               + "________________________________________________________________________\n"+token);
                                                for(int j = 1; j <i; j++){
                                                    txt += "\n";
                                                }
                                                LineaError.setText(txt+" ¡!");
                                                errores=1;
                                                break;
                                            }
                                            
                                            ENT.add(tipo);
                                        }
                                    }
                                    if(tipo.contains("DOUBLE")){
                                        
                                        while(stTipo.hasMoreTokens()){
                                            tipo = stTipo.nextToken();
                                            
                                            if(ENT.contains(tipo) || DEC.contains(tipo) || TEXT.contains(tipo)|| TAKE.contains(tipo)){
                                                Error.setText("La Variable esta repetida ("+tipo+") "+i+": \n"
                                                               + "________________________________________________________________________\n"+token);
                                                for(int j = 1; j <i; j++){
                                                    txt += "\n";
                                                }
                                                LineaError.setText(txt+" ¡!");
                                                 errores=1;
                                                break;
                                            }
                                            
                                            DEC.add(tipo);
                                        }
                                    }
                                    if(tipo.contains("CIN")){
                                       
                                        
                                        while(stTipo.hasMoreTokens()){
                                            tipo = stTipo.nextToken();
                                            
                                            if(ENT.contains(tipo) || DEC.contains(tipo) || TEXT.contains(tipo)|| TAKE.contains(tipo)){
                                                Error.setText("La Variable esta repetida ("+tipo+") "+i+": \n"
                                                               + "________________________________________________________________________\n"+token);
                                                for(int j = 1; j <i; j++){
                                                    txt += "\n";
                                                }
                                                LineaError.setText(txt+" ¡!");
                                                 errores=1;
                                                break;
                                            }
                                            
                                            TAKE.add(tipo);
                                        }
                                    }
                                    if(tipo.contains("STRING")){
                                       
                                        while(stTipo.hasMoreTokens()){
                                            tipo = stTipo.nextToken();
                                            
                                            if(ENT.contains(tipo) || DEC.contains(tipo) || TEXT.contains(tipo)|| TAKE.contains(tipo)){
                                                Error.setText("La variable esta repetida ("+tipo+") "+i+": \n"
                                                               + "________________________________________________________________________\n"+token);
                                                for(int j = 1; j <i; j++){
                                                    txt += "\n";
                                                }
                                                LineaError.setText(txt+" ¡!");
                                                 errores=1;
                                                break;
                                            }
                                            
                                            TEXT.add(tipo);
                                        }
                                    }
                                }
                                if(token.matches(defVal)){
                                    StringTokenizer stComprobar = new StringTokenizer(token," \n\t=;");
                                    String ID = stComprobar.nextToken(), comprobar = "", tok = "";
                                    //System.out.print(ID);
                                    while(stComprobar.hasMoreTokens()){
                                            comprobar += stComprobar.nextToken();
                                        }
                                    
                                    if(ENT.contains(ID)){
                                        StringTokenizer stComprobarE = new StringTokenizer(comprobar,"+*/-");
                                        while(stComprobarE.hasMoreTokens()){
                                            tok = stComprobarE.nextToken();
                                            
                                            if(tok.matches(id)){
                                                if(ENT.contains(tok));
                                                else{
                                                    Error.setText("Tipos Incompatibles ("+tok+") "+i+": \n"
                                                                    + "________________________________________________________________________\n"+token);
                                                    for(int j = 1; j <i; j++){
                                                        txt += "\n";
                                                    }
                                                    LineaError.setText(txt+" ¡!");
                                                     errores=1;
                                                    break;
                                                }
                                            }
                                            else{
                                                if(tok.matches(entero));
                                                else{
                                                    Error.setText("Tipos Incompatibles ("+tok+") "+i+": \n"
                                                                    + "________________________________________________________________________\n"+token);
                                                    for(int j = 1; j <i; j++){
                                                        txt += "\n";
                                                    }
                                                    LineaError.setText(txt+" ¡!");
                                                     errores=1;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    else {
                                        if(DEC.contains(ID)){
                                            StringTokenizer stComprobarD = new StringTokenizer(comprobar,"+*/-");
                                            while(stComprobarD.hasMoreTokens()){
                                                tok = stComprobarD.nextToken();

                                                if(tok.matches(id)){
                                                    if(DEC.contains(tok));
                                                    else{
                                                        Error.setText("Tipos Incompatibles ("+tok+") "+i+": \n"
                                                                        + "________________________________________________________________________\n"+token);
                                                        for(int j = 1; j <i; j++){
                                                            txt += "\n";
                                                        }
                                                        LineaError.setText(txt+" ¡!");
                                                         errores=1;
                                                        break;
                                                    }
                                                }
                                                else{
                                                    if(tok.matches(decimal));
                                                    else{
                                                        Error.setText("Tipos Incompatibles ("+tok+") "+i+": \n"
                                                                        + "________________________________________________________________________\n"+token);
                                                        for(int j = 1; j <i; j++){
                                                            txt += "\n";
                                                        }
                                                        LineaError.setText(txt+" ¡!");
                                                         errores=1;
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                        else {
                                            if(TEXT.contains(ID)){
                                                   if(comprobar.matches("((((\")[.\\W\\w\\s]*(\"))|("+id+"))((\\s)*(\\+)((\\s)*((\")[.\\W\\w\\s]*(\"))|("+id+")))*)"));
                                                   else {
                                                       Error.setText("Tipos Incompatibles "+i+": \n"
                                                                        + "________________________________________________________________________\n"+token);
                                                        for(int j = 1; j <i; j++){
                                                            txt += "\n";
                                                        }
                                                        LineaError.setText(txt+" ¡!");
                                                         errores=1;
                                                        break;
                                                   }
                                            }
                                            else{
                                                Error.setText("Variable no declarada "+i+": \n"
                                                                + "________________________________________________________________________\n"+token);
                                                for(int j = 1; j <i; j++){
                                                   txt += "\n";
                                                }
                                                LineaError.setText(txt+" ¡!");
                                                 errores=1;
                                                break;
                                            } 
                                        }
                                    }     
                                }
                            }
                            
                            
                            else {
                                if(token.contains("COUT")){
                                    Error.setText("Error al declarar sentencia COUT; en la linea "+i+": \n"
                                                   + "\n"+token);
                                     errores=1;
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                                if(token.contains("INTEGER") || token.contains("DOUBLE") || token.contains("STRING")){
                                    Error.setText("Error en declaracion de variables; en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                                if(token.contains("CIN")){
                                    Error.setText("Error en lectura de valor CIN  en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
            
                                else {
                                    Error.setText("Sintaxis Erronea en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                            }
                            
                            
                    }
                    
                }
                   
                else {
                    st = new StringTokenizer(txtATexto1.getText(),";{}",true);
                    while (st.hasMoreTokens()){
                        token = st.nextToken();
                        if(st.hasMoreTokens())token = token+st.nextToken();
                        if(token.matches("[.\\W\\w\\s]*(\\})") && st.countTokens() == 1){
                            String auxTok = st.nextToken();
                            token = token+(auxTok.substring(0,auxTok.indexOf("\n")));
                        }
                            StringTokenizer lin = new StringTokenizer(token,"\n",true);
                            while (lin.hasMoreTokens()){
                                e = lin.nextToken();
                                if("\n".equals(e)) i++;
                            }
                            if(eB == 1) break;

                            if((st.hasMoreTokens() == false && (start > 0 || when > 0 || it > 0)) || (start < 0 || when < 0 || it < 0)) eB = 1;
                            
                            if((token.matches(send) || token.matches(take) || token.matches(var) || token.matches(defVal) || token.matches("(\\s)*(\\$)")) && eB == 0){
                                Error.setText("Compilado Exitosamente xD lml");
                            }
                             
                            else {
                                if(token.contains("PRINT")){
                                    Error.setText("Error al declarar sentencia PRINT  en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                                if(token.contains("INTEGER") || token.contains("DOUBLE") || token.contains("STRING")){
                                    Error.setText("Error en declaracion de variables  en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                                if(token.contains("INPUT")){
                                    Error.setText("Error en lectura de valor INPUT en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                                if(token.contains("STOP}")){
                                    Error.setText("Cierre de Ciclo START incorrecto en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                                if(token.contains("START")){
                                    Error.setText("Inicio de Ciclo START incorrecto  en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                                if(token.contains("SWHEN")){
                                    Error.setText("Cierre de ciclo WHEN incorrecto  en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                                if(token.contains("WHEN")){
                                    Error.setText("Inicio de ciclo WHEN incorrecto  en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                                if(token.contains("COMPLETE")){
                                    Error.setText("Cierre de condicion IT incorrecto; en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                                if(token.contains("IT")){
                                    Error.setText("Inicio de IT incorrecto en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                                else {
                                    Error.setText("Sintaxis Erronea en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                            }
                    }
                    if(mainE == 0) {
                        Error.setText("Cierre de Clase incorrecto en la Linea "+i+": \n"
                                       + "\n"+token);
                        for(int j = 1; j <1; j++){
                            txt += "\n";
                        }
                        LineaError.setText(txt+" ¡!");
                         errores=1;
                    }
                }
            if(errores==1){
        }else{
        }
               
           
             
    }
        
        private void visual (){
                       errores=0;
        LinkedList <String> ENT = new LinkedList<>();
        LinkedList <String> DEC = new LinkedList<>();
        LinkedList <String> TEXT = new LinkedList<>();
        LinkedList <String> TAKE = new LinkedList<>();
        
        String
                simbolo = "([=<>])",
                id = "([(a-z)(A-Z)](\\w)*)",
                num = "((\\d)+)",
                dec = "((\\d)+(\\.)(\\d)+)",
                text = "((((#)[.\\W\\w\\s]*(#))|("+id+"))((\\s)*(\\+)((\\s)*((#)[.\\W\\w\\s]*(#))|("+id+")))*)",
                send = "((\\s)*PRINT(\\s)*(\\()(\\s)*((((#)[.\\W\\w\\s]*(#))|("+id+"))((\\s)*(\\+)((\\s)*((#)[.\\W\\w\\s]*(#))|("+id+")))*)(\\s)*(\\))(\\s)*(;))",
                //take = "((\\s)*INPUT(\\b)(\\s)*"+id+"((\\s)*(,(\\s)*"+id+"))*(\\s)*(;))",
                take = "((\\s)*INPUT(\\s)*(\\()(\\s)*((((#)[.\\W\\w\\s]*(#))|("+id+"))((\\s)*(\\+)((\\s)*((#)[.\\W\\w\\s]*(#))|("+id+")))*)(\\s)*(\\))(\\s)*(;))",
                operaciones = "(("+id+"|"+num+"|"+dec+")(\\s)*([+-/*](\\s)*("+id+"|"+num+"|"+dec+"))+)",
                defVal = "((\\s)*"+id+"(\\s)*=(\\s)*("+id+"|"+text+"|"+operaciones+"|"+num+"|"+dec+")(\\s)*(;))",
                defValVar = "((\\s)*"+id+"(\\s)*=(\\s)*("+id+"|"+text+"|"+operaciones+"|"+num+"|"+dec+")(\\s)*)",
                condicion = id+"(\\s)*"+simbolo+"(\\s)*("+id+"|"+num+"|"+dec+")((\\s)*([(&&)(||)](\\s)*"+id+"(\\s)*"+simbolo+"(\\s)*("+id+"|"+num+"|"+dec+")))*",
           //     var = "((\\s)*((INTEGER)|(DOUBLE)|(STRING))(\\b)(\\s)*("+id+"|"+defValVar+")((\\s)*(,(\\s)*("+id+"|"+defValVar+")))*(\\s)*(;))",
                var = "((\\s)*DIM (\\s)*("+id+"|"+defValVar+")((\\s)*(,(\\s)*("+id+"|"+defValVar+")))*(\\s)*(AS)(\\s)*((INTEGER)|(DOUBLE)|(STRING))(\\b)(\\s)*(;))",
               // main = "((\\s)*"+id+"(\\b)(\\s)*BEGIN(\\s)*(\\{)[.\\W\\w\\s]*(END(\\s)*(\\})(\\s)*)$)",   (\\s)*((INTEGER)|(DOUBLE)|(STRING))(\\b)(\\s)
              //  main2 = "((\\s)*"+id+"(\\b)(\\s)*BEGIN(\\s)*(\\{))",
              //  main3 = "((\\s)*END(\\s)*(\\})(\\s)*)",
                
            //   start2 = "((\\s)*START(\\b)(\\s)*("+id+"|"+num+")(\\b)(\\s)*(=)*("+id+"|"+num+")(\\b)(\\s)*(STEP)(\\b)(\\s)*"+num+"(\\s)*[+-]?(\\s)*(\\b)(TO)(\\b)(\\s)*("+id+"|"+num+")(\\s)*(\\{))",
          //      start3 = "((\\s)*STOP(\\s)*(\\}))",
           //     when2 = "((\\s)*WHEN(\\s)*(\\()(\\s)*"+condicion+"(\\s)*(\\))(\\s)*(\\{))",
             //  when3 = "((\\s)*SWHEN(\\s)*(\\}))",
            //    it2 = "((\\s)*IT(\\s)*(\\()(\\s)*"+condicion+"(\\s)*(\\))(\\s)*(\\{))",
             //   it3 = "((\\s)*COMPLETE(\\s)*(\\}))",
                 entero = "[0-9]*",
                decimal = "[0-9]*.[0-9]+";
        
                
                LinkedList <Integer> error = new LinkedList<>();
                StringTokenizer st = new StringTokenizer(txtATexto1.getText(),";{}",true);
                String token = "", txt = "", e;
                int i = 1, mainE = 0, start = 0, when = 0, it = 0, eB = 0;
                Error.setText("");
                
                if(!txtATexto1.getText().equals("")) {
                    
                    while (st.hasMoreTokens()){
                        token = st.nextToken();
                        if(st.hasMoreTokens())token = token+st.nextToken();
                        if(token.matches("[.\\W\\w\\s]*(\\})") && st.countTokens() == 1){
                            String auxTok = st.nextToken();
                            token = token+(auxTok.substring(0,auxTok.indexOf("\n")));
                        }
                            StringTokenizer lin = new StringTokenizer(token,"\n",true);
                            while (lin.hasMoreTokens()){
                                e = lin.nextToken();
                                if("\n".equals(e)) i++;
                            }
                            
           //                 if(token.matches(start2)) start++;
             //               if(token.matches(start3)) start--;
               //             if(token.matches(when2)) when++;
                 //           if(token.matches(when3)) when--;
                   //         if(token.matches(it2)) it++;
                     //       if(token.matches(it3)) it--;
                            if((st.hasMoreTokens() == false && (start > 0 || when > 0 || it > 0)) || (start < 0 || when < 0 || it < 0)) eB = 1;
                            
         //                   if((token.matches(send) || token.matches(take) || token.matches(var) || token.matches(defVal) || token.matches(main2) || token.matches(main3) || token.matches("(\\s)*(\\$)") || token.matches(start2) || token.matches(start3) || token.matches(when2) || token.matches(when3) || token.matches(it2) || token.matches(it3)) && eB == 0){
                            if((token.matches(send) || token.matches(take) || token.matches(var) || token.matches(defVal) ||token.matches("(\\s)*(\\$)")) && eB == 0){
                               if(token.matches(take)){
                                   
                               }
                                if(token.matches(var)){
                                    StringTokenizer stTipo = new StringTokenizer(token," ,;");
                                    String tipo = stTipo.nextToken();
                                    
                                    if(tipo.contains("INTEGER")){
                                        
                                        while(stTipo.hasMoreTokens()){
                                            tipo = stTipo.nextToken();
                                            
                                            if(ENT.contains(tipo) || DEC.contains(tipo) || TEXT.contains(tipo)|| TAKE.contains(tipo)){
                                                Error.setText("La Variable esta repetida ("+tipo+") "+i+": \n"
                                                               + "________________________________________________________________________\n"+token);
                                                for(int j = 1; j <i; j++){
                                                    txt += "\n";
                                                }
                                                LineaError.setText(txt+" ¡!");
                                                errores=1;
                                                break;
                                            }
                                            
                                            ENT.add(tipo);
                                        }
                                    }
                                    if(tipo.contains("DOUBLE")){
                                        
                                        while(stTipo.hasMoreTokens()){
                                            tipo = stTipo.nextToken();
                                            
                                            if(ENT.contains(tipo) || DEC.contains(tipo) || TEXT.contains(tipo)|| TAKE.contains(tipo)){
                                                Error.setText("La Variable esta repetida ("+tipo+") "+i+": \n"
                                                               + "________________________________________________________________________\n"+token);
                                                for(int j = 1; j <i; j++){
                                                    txt += "\n";
                                                }
                                                LineaError.setText(txt+" ¡!");
                                                 errores=1;
                                                break;
                                            }
                                            
                                            DEC.add(tipo);
                                        }
                                    }
                                    if(tipo.contains("INPUT")){
                                       
                                        
                                        while(stTipo.hasMoreTokens()){
                                            tipo = stTipo.nextToken();
                                            
                                            if(ENT.contains(tipo) || DEC.contains(tipo) || TEXT.contains(tipo)|| TAKE.contains(tipo)){
                                                Error.setText("La Variable esta repetida ("+tipo+") "+i+": \n"
                                                               + "________________________________________________________________________\n"+token);
                                                for(int j = 1; j <i; j++){
                                                    txt += "\n";
                                                }
                                                LineaError.setText(txt+" ¡!");
                                                 errores=1;
                                                break;
                                            }
                                            
                                            TAKE.add(tipo);
                                        }
                                    }
                                    if(tipo.contains("STRING")){
                                       
                                        while(stTipo.hasMoreTokens()){
                                            tipo = stTipo.nextToken();
                                            
                                            if(ENT.contains(tipo) || DEC.contains(tipo) || TEXT.contains(tipo)|| TAKE.contains(tipo)){
                                                Error.setText("La variable esta repetida ("+tipo+") "+i+": \n"
                                                               + "________________________________________________________________________\n"+token);
                                                for(int j = 1; j <i; j++){
                                                    txt += "\n";
                                                }
                                                LineaError.setText(txt+" ¡!");
                                                 errores=1;
                                                break;
                                            }
                                            
                                            TEXT.add(tipo);
                                        }
                                    }
                                }
                                if(token.matches(defVal)){
                                    StringTokenizer stComprobar = new StringTokenizer(token," \n\t=;");
                                    String ID = stComprobar.nextToken(), comprobar = "", tok = "";
                                    //System.out.print(ID);
                                    while(stComprobar.hasMoreTokens()){
                                            comprobar += stComprobar.nextToken();
                                        }
                                    
                                    if(ENT.contains(ID)){
                                        StringTokenizer stComprobarE = new StringTokenizer(comprobar,"+*/-");
                                        while(stComprobarE.hasMoreTokens()){
                                            tok = stComprobarE.nextToken();
                                            
                                            if(tok.matches(id)){
                                                if(ENT.contains(tok));
                                                else{
                                                    Error.setText("Tipos Incompatibles ("+tok+") "+i+": \n"
                                                                    + "________________________________________________________________________\n"+token);
                                                    for(int j = 1; j <i; j++){
                                                        txt += "\n";
                                                    }
                                                    LineaError.setText(txt+" ¡!");
                                                     errores=1;
                                                    break;
                                                }
                                            }
                                            else{
                                                if(tok.matches(entero));
                                                else{
                                                    Error.setText("Tipos Incompatibles ("+tok+") "+i+": \n"
                                                                    + "________________________________________________________________________\n"+token);
                                                    for(int j = 1; j <i; j++){
                                                        txt += "\n";
                                                    }
                                                    LineaError.setText(txt+" ¡!");
                                                     errores=1;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    else {
                                        if(DEC.contains(ID)){
                                            StringTokenizer stComprobarD = new StringTokenizer(comprobar,"+*/-");
                                            while(stComprobarD.hasMoreTokens()){
                                                tok = stComprobarD.nextToken();

                                                if(tok.matches(id)){
                                                    if(DEC.contains(tok));
                                                    else{
                                                        Error.setText("Tipos Incompatibles ("+tok+") "+i+": \n"
                                                                        + "________________________________________________________________________\n"+token);
                                                        for(int j = 1; j <i; j++){
                                                            txt += "\n";
                                                        }
                                                        LineaError.setText(txt+" ¡!");
                                                         errores=1;
                                                        break;
                                                    }
                                                }
                                                else{
                                                    if(tok.matches(decimal));
                                                    else{
                                                        Error.setText("Tipos Incompatibles ("+tok+") "+i+": \n"
                                                                        + "________________________________________________________________________\n"+token);
                                                        for(int j = 1; j <i; j++){
                                                            txt += "\n";
                                                        }
                                                        LineaError.setText(txt+" ¡!");
                                                         errores=1;
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                        else {
                                            if(TEXT.contains(ID)){
                                                   if(comprobar.matches("((((\")[.\\W\\w\\s]*(\"))|("+id+"))((\\s)*(\\+)((\\s)*((\")[.\\W\\w\\s]*(\"))|("+id+")))*)"));
                                                   else {
                                                       Error.setText("Tipos Incompatibles "+i+": \n"
                                                                        + "________________________________________________________________________\n"+token);
                                                        for(int j = 1; j <i; j++){
                                                            txt += "\n";
                                                        }
                                                        LineaError.setText(txt+" ¡!");
                                                         errores=1;
                                                        break;
                                                   }
                                            }
                                            else{
                                                Error.setText("Variable no declarada "+i+": \n"
                                                                + "________________________________________________________________________\n"+token);
                                                for(int j = 1; j <i; j++){
                                                   txt += "\n";
                                                }
                                                LineaError.setText(txt+" ¡!");
                                                 errores=1;
                                                break;
                                            } 
                                        }
                                    }     
                                }
                            }
                            
                            
                            else {
                                if(token.contains("PRINT")){
                                    Error.setText("Error al declarar sentencia PRINT; en la linea "+i+": \n"
                                                   + "\n"+token);
                                     errores=1;
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                                if(token.contains("INTEGER") || token.contains("DOUBLE") || token.contains("STRING")){
                                    Error.setText("Error en declaracion de variables; en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                                if(token.contains("INPUT")){
                                    Error.setText("Error en lectura de valor INPUT  en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                                if(token.contains("STOP}")){
                                    
                                    Error.setText("Cierre de Ciclo START incorrecto  en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                                if(token.contains("START")){
                                    
                                    Error.setText("Inicio de Ciclo START incorrecto  en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                                if(token.contains("SWHEN")){
                                    Error.setText("Cierre de ciclo WHEN incorrecto en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                    break;
                                }
                                if(token.contains("WHEN")){
                                    Error.setText("Inicio de ciclo WHEN incorrecto en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                                if(token.contains("COMPLETE")){
                                    
                                    Error.setText("Cierre de condicion IT incorrecto en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                                if(token.contains("IT")){
                                   
                                    Error.setText("Inicio de IT incorrecto; en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                                else {
                                    Error.setText("Sintaxis Erronea en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                            }
                            
                            
                    }
                    
                }
                   
                else {
                    st = new StringTokenizer(txtATexto1.getText(),";{}",true);
                    while (st.hasMoreTokens()){
                        token = st.nextToken();
                        if(st.hasMoreTokens())token = token+st.nextToken();
                        if(token.matches("[.\\W\\w\\s]*(\\})") && st.countTokens() == 1){
                            String auxTok = st.nextToken();
                            token = token+(auxTok.substring(0,auxTok.indexOf("\n")));
                        }
                            StringTokenizer lin = new StringTokenizer(token,"\n",true);
                            while (lin.hasMoreTokens()){
                                e = lin.nextToken();
                                if("\n".equals(e)) i++;
                            }
                            if(eB == 1) break;
//                            if(token.matches(start2)) start++;
  //                          if(token.matches(start3)) start--;
    //                        if(token.matches(when2)) when++;
      //                      if(token.matches(when3)) when--;
        //                    if(token.matches(it2)) it++;
          //                  if(token.matches(it3)) it--;
                            if((st.hasMoreTokens() == false && (start > 0 || when > 0 || it > 0)) || (start < 0 || when < 0 || it < 0)) eB = 1;
                            
                    //        if((token.matches(send) || token.matches(take) || token.matches(var) || token.matches(defVal) || token.matches(main2) || token.matches(main3) || token.matches("(\\s)*(\\$)") || token.matches(start2) || token.matches(start3) || token.matches(when2) || token.matches(when3) || token.matches(it2) || token.matches(it3)) && eB == 0){
                            if((token.matches(send) || token.matches(take) || token.matches(var) || token.matches(defVal) || token.matches("(\\s)*(\\$)")) && eB == 0){
                                Error.setText("Compilado Exitosamente xD lml");
                      //          if(token.matches(main3)) eB = 1;
                            }
                             
                            else {
                                if(token.contains("PRINT")){
                                    Error.setText("Error al declarar sentencia PRINT  en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                                if(token.contains("INTEGER") || token.contains("DOUBLE") || token.contains("STRING")){
                                    Error.setText("Error en declaracion de variables  en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                                if(token.contains("INPUT")){
                                    Error.setText("Error en lectura de valor INPUT en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                                if(token.contains("STOP}")){
                                    Error.setText("Cierre de Ciclo START incorrecto en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                                if(token.contains("START")){
                                    Error.setText("Inicio de Ciclo START incorrecto  en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                                if(token.contains("SWHEN")){
                                    Error.setText("Cierre de ciclo WHEN incorrecto  en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                                if(token.contains("WHEN")){
                                    Error.setText("Inicio de ciclo WHEN incorrecto  en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                                if(token.contains("COMPLETE")){
                                    Error.setText("Cierre de condicion IT incorrecto; en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                                if(token.contains("IT")){
                                    Error.setText("Inicio de IT incorrecto en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                                else {
                                    Error.setText("Sintaxis Erronea en la linea "+i+": \n"
                                                   + "\n"+token);
                                    for(int j = 1; j <i; j++){
                                        txt += "\n";
                                    }
                                    LineaError.setText(txt+" ¡!");
                                     errores=1;
                                    break;
                                }
                            }
                    }
                    if(mainE == 0) {
                        Error.setText("Cierre de Clase incorrecto en la Linea "+i+": \n"
                                       + "\n"+token);
                        for(int j = 1; j <1; j++){
                            txt += "\n";
                        }
                        LineaError.setText(txt+" ¡!");
                         errores=1;
                    }
                }
            if(errores==1){
            lblC.setText("ERRORES");
        }else{
            lblC.setText("CORRECTO");
        }
     
 
        
        }
    
        private void lexCMas(){
        HashMap <String,Integer> r = new HashMap<>();
        HashMap <String,Integer> op = new HashMap<>();
        HashMap <String,Integer> id = new HashMap<>();
        HashMap <String,Integer> deli = new HashMap<>();
        HashMap <String,Integer> num = new HashMap<>();
        LinkedList <String> texto = new LinkedList<>();
    

        r.put("STRING", 0);
        r.put("INTEGER", 0);
        r.put("DOUBLE", 0);
        r.put("BOOL", 0);
        r.put("CIN", 0);
        r.put("COUT", 0);
        r.put("DIM", 0);
        r.put("AS", 0);
        
        op.put("/", 0);
        op.put("*", 0);
        op.put("+", 0);
        op.put("-", 0);
        op.put("=", 0);
        op.put("^", 0);
        op.put("<", 0);
        op.put(">", 0);
        op.put("||", 0);
        op.put("&&", 0);
        
        deli.put("#", 0);
        deli.put(";", 0);
        deli.put("{", 0);
        deli.put("}", 0);
        deli.put(")", 0);
        deli.put(",", 0);
        deli.put("(",0);
        
        
         
        
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Token","Cantidad","Tipo"});
        
        StringTokenizer st = new StringTokenizer(txtATexto1.getText(),"{}();,\"=+-*/><||&&# \n\t",true);
        String token, text = "";
        while (st.hasMoreTokens()){
            token = st.nextToken();
            if(!" ".equals(token) && !"\n".equals(token) && !"\t".equals(token)){
                if (r.containsKey(token)) {
                    r.put(token, r.get(token)+1);            
                }else {
                    if (op.containsKey(token)) {
                        op.put(token, op.get(token)+1);            
                    }else {
                        if (deli.containsKey(token)){
                            deli.put(token, deli.get(token)+1);
                            if("#".equals(token)){
                                token = st.nextToken();
                                while (st.hasMoreTokens() && !"#".equals(token)){
                                    text += token;
                                    token = st.nextToken();
                                }
                                texto.add(text);
                                deli.put(token, deli.get(token)+1);
                                text = "";
                            }
                        }else {
                            if (id.containsKey(token)) {
                                id.put(token, id.get(token)+1); 
                            }else {
                                if(token.matches("([0-9]*)|([0-9]*.[0-9]+)")) {
                                    if (num.containsKey(token)) {
                                        num.put(token, num.get(token)+1);
                                    }else num.put(token, 1); 
                                }
                                else id.put(token, 1); 
                            }
                        }
                    }
                }
            }
        }
        
        Iterator<String> itr = r.keySet().iterator();
        while(itr.hasNext()){
            token = itr.next();
            if(r.get(token) > 0)model.addRow(new Object[]{token, r.get(token),"Palabra Reservada"});
        } 
        itr = op.keySet().iterator();
        while(itr.hasNext()){
            token = itr.next();
            if(op.get(token) > 0) model.addRow(new Object[]{token, op.get(token),"Operador"});
        } 
        itr = deli.keySet().iterator();
        while(itr.hasNext()){
            token = itr.next();
            if(deli.get(token) > 0) model.addRow(new Object[]{token, deli.get(token),"Delimitador"});
        } 
        itr = id.keySet().iterator();
        while(itr.hasNext()){
            token = itr.next();
            if(id.get(token) > 0) model.addRow(new Object[]{token, id.get(token),"Identificador"});
        } 
        itr = num.keySet().iterator();
        while(itr.hasNext()){
            token = itr.next();
            if(num.get(token) > 0) {
                if(token.matches("[0-9]+"))model.addRow(new Object[]{token, num.get(token),"Número"});
                if(token.matches("[0-9]+.[0-9]+"))model.addRow(new Object[]{token, num.get(token),"Número Decimal"});
            }
        }
        itr = texto.iterator();
        while(itr.hasNext()){
            model.addRow(new Object[]{itr.next(), "1","Texto"});
            
        }
        tabla.setModel(model);        
        }
        
        private void lexVisual(){
        
               HashMap <String,Integer> r = new HashMap<>();
        HashMap <String,Integer> op = new HashMap<>();
        HashMap <String,Integer> id = new HashMap<>();
        HashMap <String,Integer> deli = new HashMap<>();
        HashMap <String,Integer> num = new HashMap<>();
        LinkedList <String> texto = new LinkedList<>();

        r.put("BEGIN", 0);
        r.put("END", 0);
        r.put("STRING", 0);
        r.put("ALFA", 0);
        r.put("INTEGER", 0);
        r.put("DOUBLE", 0);
        r.put("BOOL", 0);
        r.put("LNUM", 0);
        r.put("INPUT", 0);
        r.put("PRINT", 0);
        r.put("WHEN", 0);
        r.put("IT", 0);
        r.put("IS", 0);
        r.put("START", 0);
        r.put("STEP", 0);
        r.put("TO", 0);
        r.put("STOP", 0);
        r.put("SWHEN", 0);
        r.put("COMPLETE", 0);
        r.put("DIM", 0);
        r.put("AS", 0);

        op.put("/", 0);
        op.put("*", 0);
        op.put("+", 0);
        op.put("-", 0);
        op.put("=", 0);
        op.put("^", 0);
        op.put("<", 0);
        op.put(">", 0);
        op.put("||", 0);
        op.put("&&", 0);

        deli.put("#", 0);
        deli.put(";", 0);
        deli.put("{", 0);
        deli.put("}", 0);
        deli.put(")", 0);
        deli.put(",", 0);
        deli.put("(",0);

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Token","Cantidad","Tipo"});

        StringTokenizer st = new StringTokenizer(txtATexto1.getText(),"{}();,\"=+-*/><||&&# \n\t",true);
        String token, text = "";
        while (st.hasMoreTokens()){
            token = st.nextToken();
            if(!" ".equals(token) && !"\n".equals(token) && !"\t".equals(token)){
                if (r.containsKey(token)) {
                    r.put(token, r.get(token)+1);
                }else {
                    if (op.containsKey(token)) {
                        op.put(token, op.get(token)+1);
                    }else {
                        if (deli.containsKey(token)){
                            deli.put(token, deli.get(token)+1);
                            if("#".equals(token)){
                                token = st.nextToken();
                                while (st.hasMoreTokens() && !"#".equals(token)){
                                    text += token;
                                    token = st.nextToken();
                                }
                                texto.add(text);
                                deli.put(token, deli.get(token)+1);
                                text = "";
                            }
                        }else {
                            if (id.containsKey(token)) {
                                id.put(token, id.get(token)+1);
                            }else {
                                if(token.matches("([0-9]*)|([0-9]*.[0-9]+)")) {
                                    if (num.containsKey(token)) {
                                        num.put(token, num.get(token)+1);
                                    }else num.put(token, 1);
                                }
                                else id.put(token, 1);
                            }
                        }
                    }
                }
            }
        }

        Iterator<String> itr = r.keySet().iterator();
        while(itr.hasNext()){
            token = itr.next();
            if(r.get(token) > 0)model.addRow(new Object[]{token, r.get(token),"Palabra Reservada"});
        }
        itr = op.keySet().iterator();
        while(itr.hasNext()){
            token = itr.next();
            if(op.get(token) > 0) model.addRow(new Object[]{token, op.get(token),"Operador"});
        }
        itr = deli.keySet().iterator();
        while(itr.hasNext()){
            token = itr.next();
            if(deli.get(token) > 0) model.addRow(new Object[]{token, deli.get(token),"Delimitador"});
        }
        itr = id.keySet().iterator();
        while(itr.hasNext()){
            token = itr.next();
            if(id.get(token) > 0) model.addRow(new Object[]{token, id.get(token),"Identificador"});
        }
        itr = num.keySet().iterator();
        while(itr.hasNext()){
            token = itr.next();
            if(num.get(token) > 0) {
                if(token.matches("[0-9]+"))model.addRow(new Object[]{token, num.get(token),"Número"});
                if(token.matches("[0-9]+.[0-9]+"))model.addRow(new Object[]{token, num.get(token),"Número Decimal"});
            }
        }
        itr = texto.iterator();
        while(itr.hasNext()){
            model.addRow(new Object[]{itr.next(), "1","Texto"});

        }
        tabla.setModel(model);
        
        }
        
        
        
    public Ventana() {
        initComponents();
        this.setLocationRelativeTo(null);
        //Elimina fondo de boton.
        BLimpiar.setBorder(null);
        BLimpiar.setBorderPainted(false);
        BLimpiar.setContentAreaFilled(false);
        BLimpiar.setOpaque(false);
        //Elimina fondo de boton.
        BAnalisis.setBorder(null);
        BAnalisis.setBorderPainted(false);
        BAnalisis.setContentAreaFilled(false);
        BAnalisis.setOpaque(false);
        //Elimina fondo de boton.
        BGTSImbolos.setBorder(null);
        BGTSImbolos.setBorderPainted(false);
        BGTSImbolos.setContentAreaFilled(false);
        BGTSImbolos.setOpaque(false);
        
    }
    
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("iconos/TripleA.png"));
        return retValue;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        BGTSImbolos = new javax.swing.JButton();
        BLimpiar = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        Error = new javax.swing.JEditorPane();
        BAnalisis = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        txtATexto1 = new javax.swing.JEditorPane();
        Lineas = new javax.swing.JEditorPane();
        LineaError = new javax.swing.JEditorPane();
        lblLenguaje = new javax.swing.JLabel();
        Minimizar = new javax.swing.JLabel();
        Cerrar = new javax.swing.JLabel();
        lblC = new javax.swing.JLabel();
        JLFondo = new javax.swing.JLabel();
        lblLenguaje1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setUndecorated(true);

        panel1.setBackground(new java.awt.Color(255, 255, 255));
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 102, 0), new java.awt.Color(255, 102, 0), new java.awt.Color(255, 102, 0), new java.awt.Color(255, 102, 0)));

        tabla.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 102, 0), new java.awt.Color(255, 102, 0), new java.awt.Color(255, 102, 0), new java.awt.Color(255, 102, 0)));
        tabla.setFont(new java.awt.Font("VCR OSD Mono", 0, 14)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tabla);

        panel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 40, 400, 400));

        jLabel2.setFont(new java.awt.Font("VCR OSD Mono", 2, 14)); // NOI18N
        jLabel2.setText("Texto del Archivo");
        jLabel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        BGTSImbolos.setBackground(new java.awt.Color(204, 204, 204));
        BGTSImbolos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BGTSImbolos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Table.png"))); // NOI18N
        BGTSImbolos.setToolTipText("Generar Tabla de Simbolos");
        BGTSImbolos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGTSImbolosActionPerformed(evt);
            }
        });
        panel1.add(BGTSImbolos, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 480, -1, -1));

        BLimpiar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Delete.png"))); // NOI18N
        BLimpiar.setToolTipText("Limpiar");
        BLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BLimpiarActionPerformed(evt);
            }
        });
        panel1.add(BLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 480, -1, -1));

        Error.setEditable(false);
        Error.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 102, 0), new java.awt.Color(255, 102, 0), new java.awt.Color(255, 102, 0), new java.awt.Color(255, 102, 0)));
        Error.setFont(new java.awt.Font("VCR OSD Mono", 0, 14)); // NOI18N
        Error.setForeground(new java.awt.Color(255, 102, 0));
        jScrollPane5.setViewportView(Error);

        panel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 480, 730, 94));

        BAnalisis.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BAnalisis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Component.png"))); // NOI18N
        BAnalisis.setToolTipText("Analizador");
        BAnalisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAnalisisActionPerformed(evt);
            }
        });
        panel1.add(BAnalisis, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 480, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 102, 0), new java.awt.Color(255, 102, 0), new java.awt.Color(255, 102, 0), new java.awt.Color(255, 102, 0)));

        txtATexto1.setFont(new java.awt.Font("VCR OSD Mono", 0, 14)); // NOI18N
        txtATexto1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtATexto1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtATexto1KeyReleased(evt);
            }
        });

        Lineas.setEditable(false);
        Lineas.setFont(new java.awt.Font("VCR OSD Mono", 0, 14)); // NOI18N
        Lineas.setOpaque(false);

        LineaError.setEditable(false);
        LineaError.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LineaError.setForeground(java.awt.Color.red);
        LineaError.setToolTipText("");
        LineaError.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(LineaError, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Lineas, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtATexto1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(LineaError, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                    .addComponent(Lineas, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtATexto1, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(0, 261, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        panel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 520, 400));

        lblLenguaje.setFont(new java.awt.Font("VCR OSD Mono", 1, 14)); // NOI18N
        lblLenguaje.setText("LENGUAJE");
        panel1.add(lblLenguaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 590, -1, -1));

        Minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Expand_Arrow_32px.png"))); // NOI18N
        Minimizar.setToolTipText("Minimizar");
        Minimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MinimizarMouseClicked(evt);
            }
        });
        panel1.add(Minimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 0, -1, -1));

        Cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Multiply_32px.png"))); // NOI18N
        Cerrar.setToolTipText("Cerrar");
        Cerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CerrarMouseClicked(evt);
            }
        });
        panel1.add(Cerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1350, 0, -1, -1));

        lblC.setFont(new java.awt.Font("VCR OSD Mono", 1, 12)); // NOI18N
        panel1.add(lblC, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 450, 170, 20));

        JLFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/CompT-A.png"))); // NOI18N
        JLFondo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 102, 0), new java.awt.Color(255, 102, 0), new java.awt.Color(255, 102, 0), new java.awt.Color(255, 102, 0)));
        panel1.add(JLFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1390, 680));

        lblLenguaje1.setText("LENGUAJE");
        panel1.add(lblLenguaje1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 600, -1, -1));

        jLabel4.setFont(new java.awt.Font("VCR OSD Mono", 2, 14)); // NOI18N
        jLabel4.setText("Tabla de Simbolos");
        jLabel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, -1, -1));

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 102, 0), new java.awt.Color(255, 102, 0), new java.awt.Color(255, 102, 0), new java.awt.Color(255, 102, 0)));
        jMenuBar1.setForeground(new java.awt.Color(255, 255, 255));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/49.png"))); // NOI18N
        jMenu1.setText("Opciones");
        jMenu1.setFont(new java.awt.Font("VCR OSD Mono", 2, 14)); // NOI18N

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/folder.png"))); // NOI18N
        jMenu2.setText("Abrir");
        jMenu2.setFont(new java.awt.Font("VCR OSD Mono", 2, 14)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("VCR OSD Mono", 2, 14)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/New document.png"))); // NOI18N
        jMenuItem1.setText("Archivo .TXT");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenu1.add(jMenu2);

        jMenuItem2.setFont(new java.awt.Font("VCR OSD Mono", 2, 14)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/floppydisc.png"))); // NOI18N
        jMenuItem2.setText("Salvar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       //Se crea un jfilechooser
       j.setCurrentDirectory(new File("src\\lexicosintactico"));
       j.getSelectedFile();
       j.setFileFilter(filtro);//Añado el filtro
       j.showOpenDialog(j);
       
        int contPalabra=0;//Creo un contador para las palabras
       try{
         //Aqui se manda la ruta del archivo
        path= j.getSelectedFile().getAbsolutePath();//Obtiene la Ruta
        String name=j.getSelectedFile().getName();//Obtiene el nombre
        String lectura="";
        f = new File(path);
         
        try{

            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String aux;
       //Aqui cuento cuantas palabras hay
       StreamTokenizer st=new StreamTokenizer(new FileReader(f));
       while(st.nextToken()!=StreamTokenizer.TT_EOF){
          if(st.ttype==StreamTokenizer.TT_WORD){
              contPalabra++;
              
          } 
          //lblPalabras.setText("Total de Palabras:"+contPalabra);
          //txtNombre.setText(name);
          //txtRuta.setText(path);
         
       }
       
       
       //Aqui empieza a leer el archivo linea por linea hasta que en el texto ya no haya nada
       
            while((aux = br.readLine())!=null)

               lectura = lectura+aux+"\n";//Voy acumulando todo en un string

        }catch(IOException e){}

            txtATexto1.setText(lectura);//Mando lo que resulto de la lectura
            int contador=0;
        StringTokenizer st = new StringTokenizer(txtATexto1.getText(),"\n",true);
                String Text = "",token;
                contador = 1;

                while (st.hasMoreTokens()){
                    token= st.nextToken();
                    if("\n".equals(token)) contador++;
                }

                for(int i = 1; i <= contador; i++){
                    Text += i+"\n";
                }
                Lineas.setText(Text);    
            
             //contarCaracteres(lectura);//Mando llamar el metodo de contar caracteres
             //mayusculasyminusculas(lectura);
         }catch(NullPointerException e){

            javax.swing.JOptionPane.showMessageDialog(j, "Has seleccionado cerrar programa, saliendo...");

        System.exit(0);

}
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void BGTSImbolosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGTSImbolosActionPerformed
       int v=0, c=0;
        
       ArrayList<String> listaVisual = new ArrayList<String>();
            listaVisual.add("STRING");
            listaVisual.add("INTEGER");
            listaVisual.add("DOUBLE");
            listaVisual.add("BOOL");
            listaVisual.add("INPUT");
            listaVisual.add("PRINT");
            listaVisual.add("AS");
            listaVisual.add("DIM");
       
       
       ArrayList<String> listaC = new ArrayList<String>();
            listaVisual.add("STRING");
            listaVisual.add("INTEGER");
            listaVisual.add("DOUBLE");
            listaVisual.add("BOOL");
            listaVisual.add("CIN");
            listaVisual.add("COUT");

       

        for (int i = 0; i < listaC.size(); i++) {
            if (txtATexto1.getText().contains(listaC.get(i))) {
                c++;
                
            }
        }
        
        for (int i = 0; i < listaVisual.size(); i++) {
            if (txtATexto1.getText().contains(listaVisual.get(i))) {
                v++;
            }
        }     
        
        if (v>c) {
            this.lexVisual();
      }
       else{
          this.lexCMas();
       }
        
        
        
    }//GEN-LAST:event_BGTSImbolosActionPerformed

    private void BAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAnalisisActionPerformed
    int v=0, c=0;
        
       ArrayList<String> listaVisual = new ArrayList<String>();
            listaVisual.add("STRING");
            listaVisual.add("INTEGER");
            listaVisual.add("DOUBLE");
            listaVisual.add("BOOL");
            listaVisual.add("INPUT");
            listaVisual.add("PRINT");
            listaVisual.add("AS");
            listaVisual.add("DIM");
       
       
       ArrayList<String> listaC = new ArrayList<String>();
            listaVisual.add("STRING");
            listaVisual.add("INTEGER");
            listaVisual.add("DOUBLE");
            listaVisual.add("BOOL");
            listaVisual.add("CIN");
            listaVisual.add("COUT");

       

        for (int i = 0; i < listaC.size(); i++) {
            if (txtATexto1.getText().contains(listaC.get(i))) {
                c++;
            }
        }
        
        for (int i = 0; i < listaVisual.size(); i++) {
            if (txtATexto1.getText().contains(listaVisual.get(i))) {
                v++;
            }
        }     
        
        if (v>c) {
            this.visual();
            lblLenguaje.setText("El Lenguaje es Visual");
        }
        else{
            this.cmasmas();
                        lblLenguaje.setText("El Lenguaje es C++");

        }

    
        
    }//GEN-LAST:event_BAnalisisActionPerformed

    private void BLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BLimpiarActionPerformed
        txtATexto1.setText("");
        LineaError.setText("");
        Error.setText("");
        
    }//GEN-LAST:event_BLimpiarActionPerformed

    private void txtATexto1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtATexto1KeyPressed
        StringTokenizer st = new StringTokenizer(txtATexto1.getText(),"\n",true);
        String txt = "",token;
        LineaError.setText("");
        Error.setText("");
        cont = 1;

        while (st.hasMoreTokens()){
            token= st.nextToken();
            if("\n".equals(token)) cont++;
        }

        for(int i = 1; i <= cont; i++){
            txt += i+"\n";
        }
        Lineas.setText(txt);
    }//GEN-LAST:event_txtATexto1KeyPressed

    private void txtATexto1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtATexto1KeyReleased
        StringTokenizer st = new StringTokenizer(txtATexto1.getText(),"\n",true);
        String txt = "",token;
        cont = 1;

        while (st.hasMoreTokens()){
            token= st.nextToken();
            if("\n".equals(token)) cont++;
        }

        for(int i = 1; i <= cont; i++){
            txt += i+"\n";
        }
        Lineas.setText(txt);
    }//GEN-LAST:event_txtATexto1KeyReleased

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Guardar();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void CerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CerrarMouseClicked
       Icon salir = new ImageIcon(getClass().getResource("/iconos/salir.png"));
        int dialog = JOptionPane.YES_NO_OPTION;
        int msj = JOptionPane.QUESTION_MESSAGE;
        int result = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea salir de TriAndrews?", "Cerrar TriAndrews", dialog, msj, salir);
        if (result == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_CerrarMouseClicked

    private void MinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinimizarMouseClicked
      this.setState(Ventana.ICONIFIED);
    }//GEN-LAST:event_MinimizarMouseClicked

 
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
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }
   
        public void Guardar()
    {
        try
        {
            j = new JFileChooser();
           
            
            j.setFileSelectionMode( JFileChooser.FILES_ONLY );
            FileNameExtensionFilter filtroTxt=new FileNameExtensionFilter("Documento de Texto","txt");
            j.setFileFilter(filtroTxt);
            j.setFileHidingEnabled(false);
            int fin = this.getTitle().lastIndexOf('.');
            if(fin == -1)fin = this.getTitle().length();
            j.setSelectedFile(new File(this.getTitle().substring(0,fin)));
          
            int select = j.showSaveDialog(this);
            File guarda = j.getSelectedFile();
            
            if(select == JFileChooser.APPROVE_OPTION)
            {
                if(guarda !=null)
                {
                    FileWriter  save=new FileWriter(guarda+".txt");
                    save.write(txtATexto1.getText());
                    save.close();
                    JOptionPane.showMessageDialog(null,"Se ha guardado el archivo","Información",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null,"Su archivo no se ha guardado","Advertencia",JOptionPane.WARNING_MESSAGE);
        } 
    }         
        public void Guardarbas()
    {
        try
        {
            j = new JFileChooser();
           
            
            j.setFileSelectionMode( JFileChooser.FILES_ONLY );
            FileNameExtensionFilter filtroTxt=new FileNameExtensionFilter("Archivos BAS","bas");
            j.setFileFilter(filtroTxt);
            j.setFileHidingEnabled(false);
            int fin = this.getTitle().lastIndexOf('.');
            if(fin == -1)fin = this.getTitle().length();
            j.setSelectedFile(new File(this.getTitle().substring(0,fin)));
          
            int select = j.showSaveDialog(this);
            File guarda = j.getSelectedFile();
            
            if(select == JFileChooser.APPROVE_OPTION)
            {
                if(guarda !=null)
                {
                    FileWriter  save=new FileWriter(guarda+".bas");
                    save.close();
                    JOptionPane.showMessageDialog(null,"Se ha guardado el archivo","Información",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null,"Su archivo no se ha guardado","Advertencia",JOptionPane.WARNING_MESSAGE);
        } 
    }         
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton BAnalisis;
    public javax.swing.JButton BGTSImbolos;
    private javax.swing.JButton BLimpiar;
    private javax.swing.JLabel Cerrar;
    private javax.swing.JEditorPane Error;
    private javax.swing.JLabel JLFondo;
    private javax.swing.JEditorPane LineaError;
    private javax.swing.JEditorPane Lineas;
    private javax.swing.JLabel Minimizar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblC;
    private javax.swing.JLabel lblLenguaje;
    private javax.swing.JLabel lblLenguaje1;
    private javax.swing.JPanel panel1;
    public javax.swing.JTable tabla;
    public javax.swing.JEditorPane txtATexto1;
    // End of variables declaration//GEN-END:variables
}
