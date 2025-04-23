package gui;

import dados.*;
import excecao.*;
import fachada.Veiculos;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProjetoFinalVeiculos {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Veiculos veiculos = new Veiculos();
        int op;

        do {
            System.out.println("\n=== SISTEMA DE GERENCIAMENTO DE VEÍCULOS ===");
            System.out.println("0 - Sair");
            System.out.println("1 - Cadastrar Carro");
            System.out.println("2 - Cadastrar Moto");
            System.out.println("3 - Consultar Veículo por Chassi");
            System.out.println("4 - Listar Todos os Veículos");
            System.out.println("5 - Remover Veículo");
            System.out.println("6 - Atualizar Veículo");
            System.out.print("Digite sua opção: ");
            op = teclado.nextInt();
            teclado.nextLine();
            
            switch (op) {
                case 0:
                    System.out.println("Até logo!");
                    break;
                case 1:
                    System.out.print("Marca: ");
                    String marca = teclado.nextLine();
                    System.out.print("Modelo: ");
                    String modelo = teclado.nextLine();
                    System.out.print("Ano: ");
                    int ano = teclado.nextInt();
                    System.out.print("Cor: ");
                    teclado.nextLine();
                    String cor = teclado.nextLine();
                    System.out.print("Chassi: ");
                    String chassi = teclado.nextLine();
                    System.out.print("Quilometragem: ");
                    double quilometragem = teclado.nextDouble();
                    System.out.print("Tipo de Combustível: ");
                    teclado.nextLine();
                    String tipoCombustivel = teclado.nextLine();
                    
                    Carro carro = new Carro(marca, modelo, ano, cor, chassi, quilometragem, tipoCombustivel);
                    
                    try {
                        veiculos.conectar();
                        veiculos.VeiculoAbstratoInserir(carro);
                        veiculos.commit();
                        veiculos.fechar();
                        System.out.println("Carro cadastrado com sucesso!");
                    } catch (SQLException | ChassiInvalido e) {
                        System.out.println("⚠ " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Marca: ");
                    marca = teclado.nextLine();
                    System.out.print("Modelo: ");
                    modelo = teclado.nextLine();
                    System.out.print("Ano: ");
                    ano = teclado.nextInt();
                    System.out.print("Cor: ");
                    teclado.nextLine();
                    cor = teclado.nextLine();
                    System.out.print("Chassi: ");
                    chassi = teclado.nextLine();
                    System.out.print("Quilometragem: ");
                    quilometragem = teclado.nextDouble();
                    System.out.print("Cilindradas: ");
                    int cilindradas = teclado.nextInt();
                    
                    Moto moto = new Moto(marca, modelo, ano, cor, chassi, quilometragem, cilindradas);
                    
                    try {
                        veiculos.conectar();
                        veiculos.VeiculoAbstratoInserir(moto);
                        veiculos.commit();
                        veiculos.fechar();
                        System.out.println("Moto cadastrada com sucesso!");
                    } catch (SQLException | ChassiInvalido e) {
                        System.out.println("⚠ " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Chassi: ");
                    chassi = teclado.nextLine();
                    try {
                        veiculos.conectar();
                        VeiculoAbstrato v = veiculos.VeiculoAbstratoConsultar(chassi);
                        veiculos.fechar();
                        System.out.println("\n DADOS DO VEÍCULO:");
                        System.out.println("Marca: " + v.getMarca());
                        System.out.println("Modelo: " + v.getModelo());
                        System.out.println("Ano: " + v.getAno());
                        System.out.println("Cor: " + v.getCor());
                        System.out.println("Chassi: " + v.getChassi());
                        System.out.println("Quilometragem: " + v.getQuilometragem());
                        
                        if (v instanceof Carro) {
                            Carro c = (Carro) v;
                            System.out.println("Combustível: " + c.getTipoCombustivel());
                        } else if (v instanceof Moto) {
                            Moto m = (Moto) v;
                            System.out.println("Cilindradas: " + m.getCilindradas());
                        }
                    } catch (SQLException | VeiculoNaoEncontrado e) {
                        System.out.println("⚠ " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        veiculos.conectar();
                        ArrayList<VeiculoAbstrato> lista = veiculos.VeiculoAbstratoListar();
                        veiculos.fechar();
                        System.out.println("\n LISTA DE VEÍCULOS:");
                        for (VeiculoAbstrato v : lista) {
                            System.out.println("Marca: " + v.getMarca());
                            System.out.println("Modelo: " + v.getModelo());
                            System.out.println("Tipo: " + (v instanceof Carro ? "Carro 🚗" : "Moto 🏍️"));
                            System.out.println("Chassi: " + v.getChassi());
                            System.out.println("-----------------------------");
                        }
                    } catch (SQLException | RepositorioVazio e) {
                        System.out.println("⚠ " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.print("Chassi do veículo a remover: ");
                    chassi = teclado.nextLine();
                    try {
                        veiculos.conectar();
                        veiculos.VeiculoAbstratoRemover(chassi);
                        veiculos.commit();
                        veiculos.fechar();
                        System.out.println(" Veículo removido com sucesso!");
                    } catch (SQLException | VeiculoNaoEncontrado e) {
                        System.out.println("⚠ " + e.getMessage());
                    }
                    break;
                    
                case 6:
                    System.out.print("Chassi do veículo a atualizar: ");
                    chassi = teclado.nextLine();
                    System.out.print("Nova Marca: ");
                    marca = teclado.nextLine();
                    System.out.print("Novo Modelo: ");
                    modelo = teclado.nextLine();
                    System.out.print("Novo Ano: ");
                    ano = teclado.nextInt();
                    System.out.print("Nova Cor: ");
                    teclado.nextLine();
                    cor = teclado.nextLine();
                    System.out.print("Nova Quilometragem: ");
                    quilometragem = teclado.nextDouble();

                    try {
                        veiculos.conectar();
                        VeiculoAbstrato v = veiculos.VeiculoAbstratoConsultar(chassi);
                        v.setMarca(marca);
                        v.setModelo(modelo);
                        v.setAno(ano);
                        v.setCor(cor);
                        v.setQuilometragem(quilometragem);

                        if (v instanceof Moto m) {
                            System.out.print("Novas Cilindradas: ");
                            m.setCilindradas(teclado.nextInt());
                            teclado.nextLine();
                        }

                        veiculos.VeiculoAbstratoAtualizar(v);
                        veiculos.commit();
                        veiculos.fechar();
                        System.out.println("Veículo atualizado com sucesso!");
                    } catch (SQLException | VeiculoNaoEncontrado e) {
                        System.out.println("⚠ " + e.getMessage());
                    }
                    break;
                default:
                    System.out.println("⚠ Opção inválida!");
            }
        } while (op != 0);
        
        teclado.close();
    }
}