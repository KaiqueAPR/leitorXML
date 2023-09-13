package com.kaique.leitorxml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class LeitorxmlApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeitorxmlApplication.class, args);
        try {
            LeitorxmlApplication.getCaminhoArquivo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getCaminhoArquivo() throws IOException {

        //Variável que recebe o caminho em que o arquivo está alocado.
        File arquivo = new File("C:\\Users\\kaiqu\\Documents\\Desafio\\exemplo_01.xml");
        String caminhoArquivo = "C:\\Users\\kaiqu\\Documents\\Desafio\\exemplo_01.xml";

        //Verificação de arquivo
        if(arquivo.exists()){
            System.out.println("O arquivo existe.");
        } else {
            System.out.println("O arquivo não existe.");
        }

        lerArquivo(caminhoArquivo);

    }

    public static void lerArquivo(String caminhoArquivo) throws IOException {

        Student estudanteObj = new Student();

        //Leitor do arquivo XML linha a linha
        BufferedReader buffRead = new BufferedReader(new FileReader(caminhoArquivo));
        String linha = "";
        while (linha != null) {
            System.out.println(linha);

            linha = buffRead.readLine();

            estudanteObj = popularObjeto(estudanteObj, linha);

        }
        buffRead.close();
        System.out.println();
        System.out.println(estudanteObj);
    }

    //Função responsável por pegar os valores do arquivo XML e popular o objeto
    public static Student popularObjeto(Student estudanteObj, String linha) {

        if(linha != null && !linha.isBlank()) {

            if(linha.contains("<firstname>") && linha.contains("</firstname>")){
                estudanteObj.setPrimeiroNome(linha.split("<firstname>")[1].split("</firstname>")[0]);
            } else if(linha.contains("<lastname>") && linha.contains("</lastname>")){
                estudanteObj.setUltimoNome(linha.split("<lastname>")[1].split("</lastname>")[0]);
            } else if(linha.contains("<subject>") && linha.contains("</subject>")){
                estudanteObj.setAssunto(linha.split("<subject>")[1].split("</subject>")[0]);
            } else if(linha.contains("<marks>") && linha.contains("</marks>")){
                estudanteObj.setMarcas(linha.split("<marks>")[1].split("</marks>")[0]);
            }

        }

        return estudanteObj;

    }

}
