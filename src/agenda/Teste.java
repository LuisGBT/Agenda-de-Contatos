package agenda;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Teste {
    public static void main(String[] args) {
        int opcao;
        String arqNam;
        String nome;
        String telefone;
        String email;
        String value;
        boolean encontrado = true;

        Scanner sc = new Scanner(System.in);
        Persistencia persistencia = new Persistencia("contatos.txt");
        persistencia.criarArquivo();

        try{
            do {
                System.out.println("\n=======================================");
                System.out.println("         MENU AGENDA DE CONTATOS       ");
                System.out.println("=======================================");
                System.out.println(" 1. Criar Arquivo");
                System.out.println(" 2. Selecionar Arquivo");
                System.out.println(" 3. Adicionar Contato");
                System.out.println(" 4. Listar Contatos");
                System.out.println(" 5. Editar Registro");
                System.out.println(" 6. Listar Arquivos");
                System.out.println(" 7. Buscar Contato");
                System.out.println(" 8. Excluir Arquivo");
                System.out.println(" 9. Realizar Backup");
                System.out.println(" 0. Sair");
                System.out.println("=======================================");
                System.out.print(">> Escolha uma opção: ");
                opcao = sc.nextInt();
                sc.nextLine();
                switch (opcao) {
                    case 1:
                        System.out.print("\n>> Digite o nome do novo arquivo: ");
                        arqNam = sc.nextLine();
                        Persistencia persistencia1 = new Persistencia(arqNam);
                        break;
                    case 2:
                        System.out.print("\n>> Digite o nome do arquivo para selecionar: ");
                        arqNam = sc.nextLine();
                        persistencia.setArquivo(arqNam);
                        break;
                    case 3:
                        Contato contato = new Contato();

                        System.out.print("\n>> Nome: ");
                        contato.setNome(sc.nextLine());

                        System.out.print(">> Telefone: ");
                        contato.setTelefone(sc.nextLine());

                        System.out.print(">> Email: ");
                        contato.setEmail(sc.nextLine());

                        persistencia.inserir(contato);
                        break;
                    case 4:
                        System.out.println("\n>> LISTANDO CONTATOS...");
                        persistencia.listarContatos();
                        break;
                    case 5:
                        System.out.println("\n>> Qual campo deseja editar?");
                        System.out.println(" 1 - Nome");
                        System.out.println(" 2 - Telefone");
                        System.out.println(" 3 - Email");
                        System.out.println(" 4 - Todos os campos");
                        System.out.print(">> Opção: ");
                        int opc = sc.nextInt();
                        sc.nextLine();
                        while (encontrado) {
                            switch (opc){
                                case 1:
                                    System.out.print(">> Qual nome deseja editar? ");
                                    do {
                                        nome = sc.nextLine();
                                    } while (nome.equals(""));
                                    persistencia.editar(nome, opc);
                                    encontrado = false;
                                    break;
                                case 2:
                                    System.out.print(">> Qual telefone deseja editar? ");
                                    do {
                                        telefone = sc.nextLine();
                                    } while (telefone.equals(""));
                                    persistencia.editar(telefone, opc);
                                    encontrado = false;
                                    break;
                                case 3:
                                    System.out.print(">> Qual email deseja editar? ");
                                    do {
                                        email = sc.nextLine();
                                    } while (email.equals(""));
                                    persistencia.editar(email, opc);
                                    encontrado = false;
                                    break;
                                case 4:
                                    System.out.println("\n>> Por qual registro deseja editar?");
                                    System.out.println(" 1 - Nome");
                                    System.out.println(" 2 - Telefone");
                                    System.out.println(" 3 - Email");
                                    System.out.print(">> Opção: ");
                                    opc = sc.nextInt();
                                    sc.nextLine();
                                    System.out.print(">> Digite o valor: ");
                                    do {
                                        nome = sc.nextLine();
                                    } while (nome.equals(""));
                                    persistencia.editar(nome, opc);
                                    encontrado = false;
                                    break;
                                default:
                                    System.err.println("!! ERRO: Opção inválida!!");
                                    break;
                            }
                        }
                        break;
                    case 6:
                        System.out.println("\n>> LISTANDO ARQUIVOS...");
                        persistencia.listarArquivo();
                        break;
                    case 7:
                        System.out.println("\n>> Buscar contato por:");
                        System.out.println(" 1 - Nome");
                        System.out.println(" 2 - Telefone");
                        System.out.println(" 3 - Email");
                        System.out.print(">> Opção: ");
                        do {
                            opc = sc.nextInt();
                        } while (opc == 0);
                        persistencia.buscarContato(opc);
                        break;
                    case 8:
                        System.out.print("\n>> Qual arquivo deseja excluir? ");
                        String arquivo = sc.nextLine();
                        arquivo = persistencia.validaTxt(arquivo);
                        persistencia.excluirArquivo(arquivo);
                        break;
                    case 9:
                        System.out.print("\n>> Qual arquivo deseja realizar backup? ");
                        arquivo = sc.nextLine();
                        persistencia.realizarBackup(arquivo);
                        break;
                    case 0:
                        System.out.println("\n>> Saindo do programa... Até mais!");
                        break;
                    default:
                        System.err.println("!! ERRO: Opção inválida!");
                        break;
                }
            } while (opcao != 0);
            sc.close();
        }catch (InputMismatchException e) {
            System.out.println("!! ERRO: Opção inválida");
            sc.nextLine();
        }


    }
}
