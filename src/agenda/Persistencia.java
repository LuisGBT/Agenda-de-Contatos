package agenda;

import java.io.*;
import java.util.Scanner;

public class Persistencia {
    private String arquivo;
    private final String diretorio = "C:\\Users\\playe\\IdeaProjects\\agenda";

    public void setArquivo(String arqNam) {
        if (arqNam.isEmpty()) {
            System.err.println("⚠️ Deve ser informado o nome do arquivo");
            return;
        }

        arqNam = validaTxt(arqNam);
        File arqTes = new File(diretorio + File.separator + arqNam);

        if (!arqTes.exists()) {
            System.out.println("❌ O arquivo não existe");
        } else {
            this.arquivo = arqNam;
            System.out.println("✅ O arquivo " + arqNam + " foi selecionado");
        }
    }

    public String validaTxt(String arqNam) {
        if(arqNam.toLowerCase().endsWith(".txt") || arqNam.toLowerCase().endsWith(".bkp")) {
            return arqNam;
        }else {
            return arqNam + arqNam + ".txt";
        }
    }

    public Persistencia(String arqNam) {
        if (arqNam.isEmpty()) {
            System.err.println("⚠️ Deve ser informado o nome do arquivo");
            return;
        }
        this.arquivo = validaTxt(arqNam);
        criarArquivo();
    }

    public Persistencia() {}

    public void criarArquivo() {
        try {
            File file = new File(arquivo);
            if (file.createNewFile()) {
                System.out.println("📁 Arquivo " + arquivo + " criado com sucesso");
            }
        } catch (IOException ex) {
            System.err.println("❌ Erro ao criar " + arquivo);
        }
    }

    public void inserir(Contato contato) {
        try (
                BufferedReader ler = new BufferedReader(new FileReader(arquivo));
                BufferedWriter buffer = new BufferedWriter(new FileWriter(arquivo, true))
        ) {
            String linha;
            boolean existe = false;

            while ((linha = ler.readLine()) != null) {
                String[] valores = linha.split(";");
                if (contato.getNome().equalsIgnoreCase(valores[0]) ||
                        contato.getTelefone().equalsIgnoreCase(valores[1]) ||
                        contato.getEmail().equalsIgnoreCase(valores[2])) {
                    existe = true;
                    break;
                }
            }

            if (existe) {
                System.err.println("⚠️ Contato com mesmo nome, telefone ou email já existe!");
                return;
            }

            buffer.write(contato.getNome() + ";" + contato.getTelefone() + ";" + contato.getEmail());
            buffer.newLine();
            System.out.println("✅ Contato adicionado com sucesso");
        } catch (IOException ex) {
            System.err.println("❌ Erro ao gravar contato");
        }
    }

    public void listarArquivo() {
        try {
            File dir = new File(diretorio);
            File[] arquivosTxt = dir.listFiles((dir1, name) -> name.toLowerCase().endsWith(".txt"));

            System.out.println("📚 Arquivos disponíveis:");
            if (arquivosTxt != null) {
                for (File arquivo : arquivosTxt) {
                    System.out.println("📄 " + arquivo.getName());
                }
            }
        } catch (Exception e) {
            System.err.println("❌ Erro ao listar arquivos");
        }
    }

    public void listarContatos() {
        try (
                BufferedReader buffer = new BufferedReader(new FileReader(arquivo))
        ) {
            System.out.println("📒 Lista de Contatos — Arquivo: " + arquivo);
            String linha;
            while ((linha = buffer.readLine()) != null) {
                String[] valores = linha.split(";");
                System.out.println("👤 Nome: " + valores[0] + " | ☎️ Telefone: " + valores[1] + " | 📧 Email: " + valores[2]);
            }
        } catch (IOException e) {
            System.err.println("❌ Erro ao ler contatos");
        }
    }

    public void buscarContato(int opc) {
        try {
            Scanner sc = new Scanner(System.in);

            FileReader readerArqOri = new FileReader(arquivo);
            BufferedReader bufferArqOri = new BufferedReader(readerArqOri);

            String linha = bufferArqOri.readLine();
            boolean achou = false;

            Contato contato = new Contato();

            switch (opc) {
                case 1:
                    System.out.print("🔍 Digite o nome a ser buscado: \n");
                    String nome = sc.nextLine().toLowerCase();

                    while (linha != null) {
                        String[] valores = linha.split(";");
                        if (valores[0].toLowerCase().contains(nome)) {
                            contato.setNome(valores[0]);
                            contato.setTelefone(valores[1]);
                            contato.setEmail(valores[2]);
                            achou = true;

                            System.out.println("👤 Nome: " + contato.getNome());
                            System.out.println("☎️ Telefone: " + contato.getTelefone());
                            System.out.println("📧 Email: " + contato.getEmail());
                            System.out.println("——————————————");
                        }
                        linha = bufferArqOri.readLine();
                    }

                    if (!achou) {
                        System.err.println("❌ Nome não encontrado");
                    }
                    break;

                case 2:
                    System.out.print("🔍 Digite o telefone a ser buscado: ");
                    String telefone = sc.nextLine().toLowerCase();

                    while (linha != null) {
                        String[] valores = linha.split(";");
                        if (valores[1].toLowerCase().contains(telefone)) {
                            contato.setNome(valores[0]);
                            contato.setTelefone(valores[1]);
                            contato.setEmail(valores[2]);
                            achou = true;

                            System.out.println("👤 Nome: " + contato.getNome());
                            System.out.println("☎️ Telefone: " + contato.getTelefone());
                            System.out.println("📧 Email: " + contato.getEmail());
                            System.out.println("——————————————");
                        }
                        linha = bufferArqOri.readLine();
                    }

                    if (!achou) {
                        System.err.println("❌ Telefone não encontrado");
                    }
                    break;

                case 3:
                    System.out.print("🔍 Digite o email a ser buscado: ");
                    String email = sc.nextLine().toLowerCase();

                    while (linha != null) {
                        String[] valores = linha.split(";");
                        if (valores[2].toLowerCase().contains(email)) {
                            contato.setNome(valores[0]);
                            contato.setTelefone(valores[1]);
                            contato.setEmail(valores[2]);
                            achou = true;

                            System.out.println("👤 Nome: " + contato.getNome());
                            System.out.println("☎️ Telefone: " + contato.getTelefone());
                            System.out.println("📧 Email: " + contato.getEmail());
                            System.out.println("——————————————");
                        }
                        linha = bufferArqOri.readLine();
                    }

                    if (!achou) {
                        System.err.println("❌ Email não encontrado");
                    }
                    break;

                default:
                    System.err.println("⚠️ Opção inválida!");
                    break;
            }

            bufferArqOri.close();

        } catch (Exception e) {
            System.err.println("❌ Erro ao buscar contato: " + e.getMessage());
        }
    }


    public void excluirContato(String nome) {
        File arqTemp = new File(arquivo + ".tmp");
        boolean encontrado = false;

        try (
                BufferedReader buffer = new BufferedReader(new FileReader(arquivo));
                BufferedWriter writer = new BufferedWriter(new FileWriter(arqTemp));
        ) {
            String linha;
            while ((linha = buffer.readLine()) != null) {
                String[] valores = linha.split(";");
                if (!valores[0].equalsIgnoreCase(nome)) {
                    writer.write(linha);
                    writer.newLine();
                } else {
                    encontrado = true;
                }
            }

            if (encontrado) {
                File arqOriginal = new File(arquivo);
                arqOriginal.delete();
                arqTemp.renameTo(arqOriginal);
                System.out.println("✅ Contato excluído com sucesso.");
            } else {
                arqTemp.delete();
                System.out.println("❌ Contato não encontrado.");
            }
        } catch (IOException e) {
            System.err.println("❌ Erro ao excluir contato");
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
                            System.out.print("✏️ Novo nome: ");
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
                            System.out.print("✏️ Novo telefone: ");
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
                            System.out.print("✏️ Novo email: ");
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
                            System.out.print("✏️ Novo nome: ");
                            contato.setNome(sc.nextLine());

                            System.out.print("✏️ Novo telefone: ");
                            contato.setTelefone(sc.nextLine());

                            System.out.print("✏️ Novo email: ");
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
                        System.err.println("⚠️ Opção inválida!");
                        break;
                }
                linReg = bufferArqOri.readLine();
            }

            bufferArqOri.close();
            writerArqTem.close();

            File arqOrig = new File(arquivo);
            if (arqOrig.delete()) {
                if (arqTem.renameTo(arqOrig)) {
                    System.out.println("✅ Arquivo renomeado com sucesso.");
                }
            } else {
                System.out.println("❌ Erro ao excluir arquivo original");
            }
            System.out.println(" ✅ Contato editado com sucesso.");
        } catch (IOException e) {
            System.err.println("❌ Erro ao ler contatos: " + e);
        }
    }

    public boolean existeArquivo(String nomeArquivo) {
        File dir = new File(diretorio);
        File[] arquivosTxt = dir.listFiles((dir1, name) -> name.toLowerCase().endsWith(".txt"));

        if (arquivosTxt != null) {
            for (File arq : arquivosTxt) {
                if (arq.getName().equals(nomeArquivo)) {
                    return true;
                }
            }
        }

        System.out.println("❌ Arquivo não encontrado ou inexistente");
        return false;
    }

    public void excluirArquivo(String arquivo) {
        try {
            Scanner sc = new Scanner(System.in);

            File dir = new File(diretorio);
            File[] arquivosTxt = dir.listFiles((dir1, name) -> name.toLowerCase().endsWith(".txt") || name.endsWith(".bkp"));

            boolean existe = false;
            for (int i = 0; i < arquivosTxt.length; i++) {
                if (arquivosTxt[i].getName().equals(arquivo)) {
                    existe = true;
                }
            }

            if (!existe) {
                System.out.println("❌ Arquivo não encontrado ou inexistente");
                return;
            } else {
                File arqOrig = new File(arquivo);
                int opc = 0;
                System.out.println("❓ Tem certeza que deseja excluir?\n1 - Sim\n2 - Não");
                opc = sc.nextInt();

                if (opc == 1) {
                    if (arqOrig.delete()) {
                        System.out.println("✅ Arquivo deletado com sucesso");
                    } else {
                        System.out.println("❌ Não foi possível deletar o arquivo");
                    }
                } else {
                    System.out.println("🚫 Exclusão cancelada");
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
                System.out.println("✅ Backup realizado com sucesso.");
            }
        }catch (Exception e) {
            System.out.println("❌ Erro ao realizar backup" + e.getMessage());
        }
    }
}
