package com.devops.dxc.devops.model;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

public class Util {

    /**
     * Método para cacular el 10% del ahorro en la AFP.  Las reglas de negocio se pueden conocer en 
     * https://www.previsionsocial.gob.cl/sps/preguntas-frecuentes-nuevo-retiro-seguro-10/
     * 
     * @param ahorro
     * @param sueldo
     * @return
     */
    public static int getDxc(int ahorro, int sueldo){
        if(((ahorro*0.1)/getUf()) > 150 ){
            return (int) (150*getUf()) ;
        } else if((ahorro*0.1)<=1000000 && ahorro >=1000000){
            return (int) 1000000;
        } else if( ahorro <=1000000){
            return (int) ahorro;
        } else {
            return (int) (ahorro*0.1);
        }
    }

    /**
     * Método para cacular el impuesto
     * https://www.previsionsocial.gob.cl/sps/preguntas-frecuentes-nuevo-retiro-seguro-10/
     * 
     * @param ahorro
     * @param sueldo
     * @return
     */
    public static int getImpuesto(int ahorro, int sueldo){
        // Agregar cálculo.
        int impuesto = (int)(ahorro * 0.19);
        return impuesto;
    }

    /**
     * Método para calcular el saldo restante
     * https://www.previsionsocial.gob.cl/sps/preguntas-frecuentes-nuevo-retiro-seguro-10/
     *
     * @param ahorro
     * @param sueldo
     * @return
     */
    public static int getSaldo(int ahorro, int sueldo){
        int dxc = getDxc(ahorro,sueldo);
        int impuesto = getDxc(ahorro,sueldo);
        // Agregar cálculo.
        return ahorro - dxc - impuesto;
    }

    /**
     * Método que retorna el valor de la UF.  Este método debe ser refactorizado por una integración a un servicio
     * que retorne la UF en tiempo real.  Por ejemplo mindicador.cl
     * @return
     */
    public static int getUf(){
        System.out.println("Obteniendo UF");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> call= restTemplate.getForEntity("https://mindicador.cl/api/uf" ,String.class);
        Gson gson = new Gson();
        //MiIndicador miIndicador = gson.fromJson(call.getBody().toLowerCase(), MiIndicador.class);
        //int valorUf = (miIndicador.getSerie().get(0).getValor()).intValue();
        int valorUf = 27000;
        System.out.println("El valor de la UF es: " + valorUf);
        return valorUf;
    }
    
}
