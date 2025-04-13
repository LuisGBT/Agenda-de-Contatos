package agenda;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Persistencia {
    private String arquivo;
    private final String diretorio = "C:\\Users\\playe\\IdeaProjects\\agenda";

    public void setArquivo(String arqNam) {
        if (arqNam.isEmpty()) {
            System.err.println("Deve ser informado o nome do arquivo");
        } else {
            if (arqNam.toLowerCase().endsWith(".txt")) {
                File arqTes = new File(diretorio + File.separator + arqNam);
                if (!arqTes.exists()) {
                    System.out.println("O arquivo não existe");
                } else {
                    this.arquivo = arqNam;
                    System.out.println("O arquivo " + arqNam + " foi selecionado");
                }
            } else {
                arqNam = arqNam + ".txt";
                File arqTes = new File(diretorio + File.separator + arqNam);
                if (!arqTes.exists()) {
                    System.out.println("O arquivo não existe");
                } else {
                    System.out.println("O arquivo " + arqNam + " foi selecionado");
                    this.arquivo = arqNam;
                }
            }
        }
    }

    public String validaTxt(String arqNam) {
        if (!arqNam.toLowerCase().endsWith(".txt")) {
            arqNam = arqNam + ".txt";
        }
        return arqNam;
    }

    public Persistencia(String arqNam) {
        if (arqNam.isEmpty()) {
            System.err.println("Deve ser informado o nome do arquivo");
            return;
        } else {
          this.arquivo = (arqNam = validaTxt(arqNam));
        }
        this.criarArquivo();
    }

    public Persistencia() {}

    public void criarArquivo() {
        try {
            File file = new File(arquivo);
            if (file.createNewFile()) {
                System.out.println("Arquivo " + this.arquivo + " criado com sucesso");
            }
        } catch (IOException ex) {
            System.out.println("Erro ao criar " + this.arquivo);
        }
    }

    public void inserir(Contato contato) {
        try {
            FileWriter escritor = new FileWriter(arquivo, true);

            FileReader leitor = new FileReader(arquivo);
            BufferedReader ler = new BufferedReader(leitor);

            String linha = ler.readLine();
            boolean existe = false;

            while (linha != null) {
                String[] valores = linha.split(";");
                String nomeExistente = valores[0];
                String telefoneExistente = valores[1];
                String emailExistente = valores[2];

                if (contato.getNome().equalsIgnoreCase(nomeExistente) ||
                        contato.getTelefone().equalsIgnoreCase(telefoneExistente) ||
                        contato.getEmail().equalsIgnoreCase(emailExistente)) {
                    existe = true;
                    break;
                }
                linha = ler.readLine();
            }

            if (existe) {
                System.err.println("Contato com o mesmo nome, telefone, ou email ja existe!!");
                return;
            }

            BufferedWriter buffer = new BufferedWriter(escritor);
            buffer.write(contato.getNome() + ";" +
                    contato.getTelefone() + ";" +
                    contato.getEmail());

            buffer.newLine();
            buffer.close();
            ler.close();
            escritor.close();
            System.out.println("Contato adicionado com sucesso");
        } catch (IOException ex) {
            System.out.println("Erro ao gravar contato");
        }
    }

    public void listarArquivo() {
        try {
            File dir = new File(diretorio);
            File[] arquivosTxt = dir.listFiles((dir1, name) -> name.toLowerCase().endsWith(".txt"));

            for (int i = 0; i < arquivosTxt.length; i++) {
                System.out.println(arquivosTxt[i].getName());
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar arquivo");
        }
    }

    public void listarContatos() {
        try {
            FileReader leitor = new FileReader(arquivo);
            BufferedReader buffer = new BufferedReader(leitor);
            String linha = buffer.readLine();
            System.out.println("-------Lista de Contatos-------");
            System.out.println("ARQUIVO: " + arquivo);
            while (linha != null) {
                String valores[] = linha.split(";");
                Contato contato = new Contato();
                contato.setNome(valores[0]);
                contato.setTelefone(valores[1]);
                contato.setEmail(valores[2]);
                System.out.println("Nome:" + contato.getNome() + " Telefone:" + contato.getTelefone() + " Email:" + contato.getEmail());
                linha = buffer.readLine();
            }
            buffer.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler contatos");
        }
    }

    public void buscarContato(int opc) {
        try {
            Scanner sc = new Scanner(System.in);

            FileReader readerArqOri = new FileReader(arquivo);
            BufferedReader bufferArqOri = new BufferedReader(readerArqOri);

            String linha = bufferArqOri.readLine();

            Contato contato = new Contato();

            /*while (linha != null) {
                String[] valores = linha.split(";");
                linha = bufferArqOri.readLine();
            }*/

            switch (opc) {
                case 1:
                    System.out.println("Qual nome deseja buscar? ");
                    String nome = sc.nextLine().toLowerCase();
                    boolean achou = false;

                    while (linha != null) {
                        String[] valores = linha.split(";");
                        if (valores[0].toLowerCase().contains(nome)) {

                            contato.setNome(valores[0]);
                            contato.setTelefone(valores[1]);
                            contato.setEmail(valores[2]);
                            achou = true;
                            System.out.println(" Nome:" + contato.getNome() + "\n Telefone:" + contato.getTelefone() + "\n Email:" + contato.getEmail() + "\n");
                        }

                        linha = bufferArqOri.readLine();
                    }
                    if (!achou) {
                        System.err.println("Nome não encontrado");
                    }


                    break;
                case 2:
                    System.out.println("Qual telefone deseja buscar? ");
                    String telefone = sc.nextLine().toLowerCase();
                    achou = false;

                    while (linha != null) {
                        String[] valores = linha.split(";");
                        if (valores[1].toLowerCase().contains(telefone)) {

                            contato.setNome(valores[0]);
                            contato.setTelefone(valores[1]);
                            contato.setEmail(valores[2]);
                            achou = true;
                            System.out.println(" Nome:" + contato.getNome() + "\n Telefone:" + contato.getTelefone() + "\n Email:" + contato.getEmail() + "\n");
                        }
                        linha = bufferArqOri.readLine();
                    }
                    if (!achou) {
                        System.err.println("Telefone não encontrado");
                    }
                    break;
                case 3:
                    System.out.println("Qual email deseja buscar? ");
                    String email = sc.nextLine().toLowerCase();
                    achou = false;
                    while (linha != null) {
                        String[] valores = linha.split(";");
                        if (valores[2].toLowerCase().contains(email)) {

                            contato.setNome(valores[0]);
                            contato.setTelefone(valores[1]);
                            contato.setEmail(valores[2]);
                            achou = true;
                            System.out.println(" Nome:" + contato.getNome() + "\n Telefone:" + contato.getTelefone() + "\n Email:" + contato.getEmail() + "\n");
                        }
                        linha = bufferArqOri.readLine();
                    }
                    if (!achou) {
                        System.err.println("Email não encontrado");
                    }
                    break;
                default:
                    System.err.println("ERRO!! Opção invalida!");
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar contato" + e);
        }
    }

    public void editar(String nome, int opc) {
        try {
            FileReader ReaderArqOri = new FileReader(arquivo);
            BufferedReader bufferArqOri = new BufferedReader(ReaderArqOri);

            File arqTem = new File(arquivo + ".tmp");
            BufferedWriter writerArqTem = new BufferedWriter(new FileWriter(arqTem));

            String linReg = bufferArqOri.readLine();

            Contato contato = new Contato();

            while (linReg != null) {
                String novoNome;
                String novoTelefone;
                String novoEmail;

                String valores[] = linReg.split(";");
                Scanner sc = new Scanner(System.in);

                switch (opc) {
                    case 1:
                        if (nome.equalsIgnoreCase(valores[0])) {
                            System.out.println("Digite um novo nome: ");
                            contato.setNome(sc.nextLine());
                            novoTelefone = valores[1];
                            novoEmail = valores[2];

                            writerArqTem.write(contato.getNome() + ";" +
                                    novoTelefone + ";" +
                                    novoEmail);
                            writerArqTem.newLine();
                        } else {
                            //Se o nome não for igual, copia a linha para o arquivo temporario (Isso irá manter o arquivo original)
                            writerArqTem.write(linReg);
                            writerArqTem.newLine();
                        }
                        break;
                    case 2:
                        if (nome.equalsIgnoreCase(valores[1])) {
                            System.out.println("Digite um novo telefone: ");
                            contato.setTelefone(sc.nextLine());
                            novoNome = valores[0];
                            novoEmail = valores[2];

                            writerArqTem.write(novoNome + ";" +
                                    contato.getTelefone() + ";" +
                                    novoEmail);
                            writerArqTem.newLine();
                        } else {
                            //Se o nome não for igual, copia a linha para o arquivo temporario (Isso irá manter o arquivo original)
                            writerArqTem.write(linReg);
                            writerArqTem.newLine();
                        }
                        break;
                    case 3:
                        if (nome.equalsIgnoreCase(valores[2])) {
                            System.out.println("Digite um novo email: ");
                            contato.setEmail(sc.nextLine());
                            novoNome = valores[0];
                            novoTelefone = valores[1];

                            writerArqTem.write(novoNome + ";" +
                                    novoTelefone + ";" +
                                    contato.getEmail());
                            writerArqTem.newLine();
                        } else {
                            //Se o nome não for igual, copia a linha para o arquivo temporario (Isso irá manter o arquivo original)
                            writerArqTem.write(linReg);
                            writerArqTem.newLine();
                        }
                        break;
                    case 4:
                        if (nome.equalsIgnoreCase(valores[0])) {
                            System.out.println("Digite um novo nome: ");
                            contato.setNome(sc.nextLine());

                            System.out.println("Digite um novo telefone: ");
                            contato.setTelefone(sc.nextLine());

                            System.out.println("Digite um novo email: ");
                            contato.setEmail(sc.nextLine());

                            writerArqTem.write(contato.getNome() + ";" +
                                    contato.getTelefone() + ";" +
                                    contato.getEmail());
                            writerArqTem.newLine();

                        } else {
                            //Se o nome não for igual, copia a linha para o arquivo temporario (Isso irá manter o arquivo original)
                            writerArqTem.write(linReg);
                            writerArqTem.newLine();
                        }
                        break;
                    default:
                        System.err.println("Opcao Invalida!!");
                        break;
                }
                linReg = bufferArqOri.readLine();
            }

            bufferArqOri.close();
            writerArqTem.close();

            File arqOrig = new File(arquivo);
            if (arqOrig.delete()) {
                if (arqTem.renameTo(arqOrig)) {
                    System.out.println("Arquivo renomeado com sucesso");
                }
            } else {
                System.out.println("Erro ao excluir arquivo original");
            }
            System.out.println("Arquivo editado com sucesso");
        } catch (IOException e) {
            System.out.println("Erro ao ler contatos");
        }
    }

    public boolean existeArquivo(String arquivo) {
        File dir = new File(diretorio);
        File[] arquivosTxt = dir.listFiles((dir1, name) -> name.toLowerCase().endsWith(".txt"));

        boolean existe = false;
        for (int i = 0; i < arquivosTxt.length; i++) {
            if (arquivosTxt[i].getName().equals(arquivo)) {
                existe = true;
            }
        }

        if (!existe) {
            System.out.println("Arquivo não encontrado ou inexistente");
            return false;
        }else {
            return true;
        }
    }

    public void excluirArquivo(String arquivo) {
        try {
            Scanner sc = new Scanner(System.in);

            File dir = new File(diretorio);
            File[] arquivosTxt = dir.listFiles((dir1, name) -> name.toLowerCase().endsWith(".txt"));

            boolean existe = false;
            for (int i = 0; i < arquivosTxt.length; i++) {
                if (arquivosTxt[i].getName().equals(arquivo)) {
                    existe = true;
                }
            }

            if (!existe) {
                System.out.println("Arquivo não encontrado ou inexistente");
                return;
            } else {
                File arqOrig = new File(arquivo);
                int opc = 0;
                System.out.println("Tem certeza que deseja excluir?\n 1 - Sim\n 2 - Nao");
                opc = sc.nextInt();

                if (opc == 1) {
                    if (arqOrig.delete()) {
                        System.out.println("Arquivo deletado com sucesso");
                    } else {
                        System.out.println("Não foi possivel deletar o arquivo");
                    }
                } else {
                    System.out.println("Exclusão de arquivo cancelada");
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao excluir arquivo: " + e.getMessage());
        }
    }

    public void realizarBackup(String arquivo) {
        try {
            arquivo = validaTxt(arquivo);
            if(existeArquivo(arquivo)){

                FileReader ReaderArqOri = new FileReader(arquivo);
                BufferedReader bufferArqOri = new BufferedReader(ReaderArqOri);

                File arqTem = new File(arquivo + ".bkp");
                BufferedWriter writerArqTem = new BufferedWriter(new FileWriter(arqTem));


                String linReg = bufferArqOri.readLine();

                while (linReg != null) {
                    writerArqTem.write(linReg);
                    linReg = bufferArqOri.readLine();
                }
                System.out.println("backup realizado com sucesso");
            }
        }catch (Exception e) {
            System.out.println("Erro ao realizar backup: " + e.getMessage());
        }
    }
}
