import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kohsuke.github.GHTag;
import org.kohsuke.github.GHUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GithubAPITest {

    GithubAPI github;
    String token;
    String repositoryOwner;
    String repositoryName;

    @BeforeEach
    public void create() throws IOException {
        token = "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";
        repositoryOwner = "miguelrato18";
        repositoryName = "ES-LETI-1Sem-2021-Grupo11";

        github = new GithubAPI(token,repositoryOwner,repositoryName);
    }

    @Test
    public void getREADMETest() throws IOException {
        String readme ="# ES-LETI-1Sem-2021-Grupo11\n" +
                       "### Elementos do grupo:\n" +
                       "> - 93019 → Filipe Gonçalves\n" +
                       "> - 92689 → Pedro Santana\n" +
                       "> - 92657 → Miguel Rato\n" +
                       "> - 92553 → Daniel Campos\n" +
                       "\n" +
                       "### Erros encontrados:\n" +
                       "   \n" +
                       "### Funcionalidades por Implementar/Incompletas:\n";

        Assertions.assertEquals(readme, github.getREADME());
    }

    //TODO TESTS para as funções: getCommitInfoByMember

    @Test
    public void getCommitsInfoByMemberTest() throws IOException {
        List<GHUser> members = github.GitRepo.getCollaborators().stream().toList();
        List<String> commitsByMember = new ArrayList<>();
        commitsByMember.add("miguelrato18\n" +
                            "Fri Nov 26 15:04:52 WET 2021\n" +
                            "Maven com API GitHub\n" +
                            "\n" +
                            "miguelrato18\n" +
                            "Fri Nov 26 14:59:19 WET 2021\n" +
                            "Merge remote-tracking branch 'origin/PAAMT' into Rato\n" +
                            "\n" +
                            "# Conflicts:\n" +
                            "#\t.idea/compiler.xml\n" +
                            "#\t.idea/workspace.xml\n" +
                            "#\tProject.iml\n" +
                            "#\ttarget/classes/GUI_Trello.class\n" +
                            "\n" +
                            "miguelrato18\n" +
                            "Mon Nov 22 14:46:15 WET 2021\n" +
                            "GUI de acesso a github e trello desenvolvida(parcialmente)V2\n" +
                            "\n" +
                            "miguelrato18\n" +
                            "Mon Nov 22 14:23:12 WET 2021\n" +
                            "GUI de acesso a github e trello desenvolvida(parcialmente)\n" +
                            "\n" +"miguelrato18\n" +
                            "Fri Nov 19 15:24:06 WET 2021\n" +
                            "update para ficar com mesma versao de colegas\n" +
                            "\n" +
                            "miguelrato18\n" +
                            "Fri Nov 19 15:16:40 WET 2021\n" +
                            "Merge remote-tracking branch 'origin/PAAMT' into Rato\n" +
                            "\n" +"miguelrato18\n" +
                            "Fri Nov 19 15:16:33 WET 2021\n" +
                            "github button works\n" +
                            "\n" +"miguelrato18\n" +
                            "Fri Nov 19 14:11:37 WET 2021\n" +
                            "Merge remote-tracking branch 'origin/PAAMT' into Rato\n" +
                            "\n" +
                            "# Conflicts:\n" +
                            "#\t.idea/compiler.xml\n" +
                            "#\tProject.iml\n" +
                            "#\tsrc/main/java/GUI.form\n" +
                            "#\tsrc/main/java/GUI.java\n" +
                            "#\ttarget/classes/GUI$1.class\n" +
                            "#\ttarget/classes/GUI.class\n" +
                            "\n" +        "miguelrato18\n" +
                            "Fri Oct 22 15:49:03 WEST 2021\n" +
                            "github button works\n" +
                            "\n" +"miguelrato18\n" +
                            "Fri Oct 22 15:42:25 WEST 2021\n" +
                            "msg\n" +
                            "\n" +
                            "miguelrato18\n" +
                            "Fri Oct 22 14:51:54 WEST 2021\n" +
                            "msg\n" +
                            "\n" +
                            "miguelrato18\n" +
                            "Fri Oct 22 14:50:23 WEST 2021\n" +
                            "Merge branch 'master' into Rato\n" +
                            "\n" +
                            "# Conflicts:\n" +
                            "#\t.idea/ES-LETI-1Sem-2021-Grupo11.iml\n" +
                            "#\t.idea/misc.xml\n" +
                            "#\tteste\n" +
                            "\n" +
                            "miguelrato18\n" +
                            "Fri Oct 22 14:48:52 WEST 2021\n" +
                            "msg\n" +
                            "\n" +
                            "miguelrato18\n" +
                            "Fri Oct 22 14:37:13 WEST 2021\n" +
                            "Merge remote-tracking branch 'origin/master' into Rato\n" +
                            "\n" +
                            "# Conflicts:\n" +
                            "#\tteste\n" +
                            "\n" +"miguelrato18\n" +
                            "Fri Oct 15 16:32:39 WEST 2021\n" +
                            "test\n" +
                            "\n" +
                            "miguelrato18\n" +
                            "Fri Oct 15 16:28:10 WEST 2021\n" +
                            "Merge branch 'PAAMT' into Rato\n" +
                            "\n" +
                            "# Conflicts:\n" +
                            "#\t.idea/ES-LETI-1Sem-2021-Grupo11.iml\n" +
                            "#\t.idea/misc.xml\n" +
                            "#\tteste\n" +
                            "\n" +"miguelrato18\n" +
                            "Wed Oct 13 16:59:52 WEST 2021\n" +
                            "test\n" +
                            "\n" +
                            "miguelrato18\n" +
                            "Wed Oct 13 16:52:16 WEST 2021\n" +
                            "test\n" +
                            "\n" +
                            "miguelrato18\n" +
                            "Wed Oct 13 16:50:59 WEST 2021\n" +
                            "test\n" +
                            "\n" +
                            "miguelrato18\n" +
                            "Wed Oct 13 16:50:19 WEST 2021\n" +
                            "test\n" +
                            "\n" +
                            "miguelrato18\n" +
                            "Wed Oct 13 16:49:45 WEST 2021\n" +
                            "test\n" +
                            "\n" +"miguelrato18\n" +
                            "Sat Oct 09 18:05:28 WEST 2021\n" +
                            "status message\n" +
                            "\n");
        commitsByMember.add("DanielMoreiraCampos\n" +
                            "Sat Oct 09 18:18:56 WEST 2021\n" +
                            "Mensagem sobre o estado atual deste projeto\n" +
                            "\n");
        commitsByMember.add("fasgs1-iscte\n" +
                            "Wed Dec 01 19:03:28 WET 2021\n" +
                            "Merge remote-tracking branch 'origin/master'\n" +
                            "\n" +"fasgs1-iscte\n" +
                            "Wed Dec 01 19:03:00 WET 2021\n" +
                            "Finalização da TrelloAPI\n" +
                            "\n" +"fasgs1-iscte\n" +
                            "Wed Dec 01 18:59:19 WET 2021\n" +
                            "Merge remote-tracking branch 'origin/fasgs1'\n" +
                            "\n" +
                            "# Conflicts:\n" +
                            "#\t.idea/workspace.xml\n" +
                            "#\ttarget/classes/Main.class\n" +
                            "\n" +
                            "fasgs1-iscte\n" +
                            "Wed Dec 01 18:57:33 WET 2021\n" +
                            "Finalização da TrelloAPI\n" +
                            "\n" +
                            "fasgs1-iscte\n" +
                            "Wed Dec 01 15:31:35 WET 2021\n" +
                            "Update do Trello Api\n" +
                            "\n" +
                            "fasgs1-iscte\n" +
                            "Wed Dec 01 13:54:42 WET 2021\n" +
                            "Update do Trello Api\n" +
                            "\n" +
                            "fasgs1-iscte\n" +
                            "Wed Dec 01 11:29:59 WET 2021\n" +
                            "Update do Trello Api\n" +
                            "\n" +"fasgs1-iscte\n" +
                            "Fri Nov 26 15:57:09 WET 2021\n" +
                            "Update do Trello Api\n" +
                            "\n" +
                            "fasgs1-iscte\n" +
                            "Fri Nov 26 15:48:30 WET 2021\n" +
                            "Merge remote-tracking branch 'origin/Rato' into fasgs1\n" +
                            "\n" +
                            "# Conflicts:\n" +
                            "#\t.idea/workspace.xml\n" +
                            "#\ttarget/classes/Main.class\n" +
                            "\n" +
                            "fasgs1-iscte\n" +
                            "Fri Nov 26 15:45:03 WET 2021\n" +
                            "Merge remote-tracking branch 'origin/PAAMT' into fasgs1\n" +
                            "\n" +
                            "# Conflicts:\n" +
                            "#\t.idea/libraries/Maven__commons_codec_commons_codec_1_9.xml\n" +
                            "#\t.idea/libraries/Maven__org_apache_httpcomponents_httpasyncclient_4_1_1.xml\n" +
                            "#\t.idea/libraries/Maven__org_apache_httpcomponents_httpclient_4_5_2.xml\n" +
                            "#\t.idea/libraries/Maven__org_apache_httpcomponents_httpcore_4_4_4.xml\n" +
                            "#\t.idea/libraries/Maven__org_apache_httpcomponents_httpcore_nio_4_4_4.xml\n" +
                            "#\t.idea/libraries/Maven__org_apache_httpcomponents_httpmime_4_5_2.xml\n" +
                            "#\t.idea/workspace.xml\n" +
                            "#\tProject.iml\n" +
                            "#\tpom.xml\n" +
                            "#\tsrc/main/java/Main.java\n" +
                            "#\tsrc/main/java/TrelloAPI.java\n" +
                            "#\ttarget/classes/Main.class\n" +
                            "#\ttarget/classes/TrelloAPI.class\n" +
                            "\n" +"fasgs1-iscte\n" +
                            "Sun Nov 21 20:24:00 WET 2021\n" +
                            "Update do Trello Api\n" +
                            "\n" +
                            "fasgs1-iscte\n" +
                            "Sun Nov 21 20:23:16 WET 2021\n" +
                            "Merge remote-tracking branch 'origin/fasgs1'\n" +
                            "\n" +
                            "# Conflicts:\n" +
                            "#\t.idea/workspace.xml\n" +
                            "#\tpom.xml\n" +
                            "#\ttarget/classes/Main.class\n" +
                            "\n" +
                            "fasgs1-iscte\n" +
                            "Sun Nov 21 20:22:41 WET 2021\n" +
                            "Update do Trello Api\n" +
                            "\n" +
                            "fasgs1-iscte\n" +
                            "Sun Nov 21 19:05:39 WET 2021\n" +
                            "Update do Trello Api\n" +
                            "\n" +
                            "fasgs1-iscte\n" +
                            "Sun Nov 21 19:05:18 WET 2021\n" +
                            "Update do Trello Api\n" +
                            "\n" +
                            "fasgs1-iscte\n" +
                            "Sun Nov 21 19:03:40 WET 2021\n" +
                            "Update do Trello Api\n" +
                            "\n" +
                            "fasgs1-iscte\n" +
                            "Sun Nov 21 18:52:55 WET 2021\n" +
                            "Merge remote-tracking branch 'origin/fasgs1'\n" +
                            "\n" +
                            "# Conflicts:\n" +
                            "#\tsrc/main/java/Main.java\n" +
                            "#\ttarget/classes/GUI_Trello.class\n" +
                            "#\ttarget/classes/Main.class\n" +
                            "\n" +"fasgs1-iscte\n" +
                            "Sun Nov 21 18:38:08 WET 2021\n" +
                            "Update do Trello Api\n" +
                            "\n" +"fasgs1-iscte\n" +
                            "Fri Nov 19 15:16:39 WET 2021\n" +
                            "Merge remote-tracking branch 'origin/PAAMT' into fasgs1\n" +
                            "\n" +
                            "# Conflicts:\n" +
                            "#\t.idea/compiler.xml\n" +
                            "#\tProject.iml\n" +
                            "\n" +"fasgs1-iscte\n" +
                            "Fri Nov 19 14:48:20 WET 2021\n" +
                            "Update do pom.xml com as dependencias necessarias e começo da implepentação da Api do Trelloo\n" +
                            "\n" +        "fasgs1-iscte\n" +
                            "Tue Oct 19 14:02:19 WEST 2021\n" +
                            "Create README.md\n" +
                            "\n" +"fasgs1-iscte\n" +
                            "Fri Oct 15 14:45:29 WEST 2021\n" +
                            "ifrcdkms xfrecd\n" +
                            "\n");
        commitsByMember.add("paamt-iscte\n" +
                            "Fri Dec 03 15:49:57 WET 2021\n" +
                            "Update README.md\n" +
                            "\n"+"paamt-iscte\n" +
                            "Wed Dec 01 00:31:11 WET 2021\n" +
                            "Update README.md\n" +
                            "\n" +"paamt-iscte\n" +
                            "Sun Nov 21 20:26:13 WET 2021\n" +
                            "+Update\n" +
                            "\n" +"paamt-iscte\n" +
                            "Sun Nov 21 18:44:06 WET 2021\n" +
                            "+update\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Sun Nov 21 18:43:59 WET 2021\n" +
                            "+update\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Sun Nov 21 18:42:43 WET 2021\n" +
                            "Merge remote-tracking branch 'origin/PAAMT'\n" +
                            "\n" +
                            "# Conflicts:\n" +
                            "#\t.idea/compiler.xml\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Sun Nov 21 18:41:08 WET 2021\n" +
                            "+update\n" +
                            "\n" +"paamt-iscte\n" +
                            "Fri Nov 19 17:11:59 WET 2021\n" +
                            "update\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Fri Nov 19 16:23:24 WET 2021\n" +
                            "Novo update da GUI do trello/github:\n" +
                            " +Adicionado o campo TrelloLogin [GUI_Trello];\n" +
                            " +Adicionado o campo GithubLogin [GUI_Github];\n" +
                            " +Adicionado Getters para token, key e user [GUI_Trello];\n" +
                            " +Adicionado Getters para token, repositoryName, repositoryOwner [GUI_Github];\n" +
                            "\n" +
                            " +Update ao campo SetPassword [GUI_Trello/GUI_Github];\n" +
                            " +Update ao actionPerformed do button_login [GUI_Trello/GUI_GitHub];\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Fri Nov 19 16:22:31 WET 2021\n" +
                            "Novo update da GUI do trello/github:\n" +
                            " +Adicionado o campo TrelloLogin [GUI_Trello];\n" +
                            " +Adicionado o campo GithubLogin [GUI_Github];\n" +
                            " +Adicionado Getters para token, key e user [GUI_Trello];\n" +
                            " +Adicionado Getters para token, repositoryName, repositoryOwner [GUI_Github];\n" +
                            "\n" +
                            " +Update ao campo SetPassword [GUI_Trello/GUI_Github];\n" +
                            " +Update ao actionPerformed do button_login [GUI_Trello/GUI_GitHub];\n" +
                            "\n" +"paamt-iscte\n" +
                            "Fri Nov 19 15:15:08 WET 2021\n" +
                            "novo update da GUI do trello\n" +
                            "+Adicionado o campo User para a API\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Fri Nov 19 15:13:26 WET 2021\n" +
                            "novo update da GUI do trello\n" +
                            "+Adicionado o campo User para a API\n" +
                            "\n" +"paamt-iscte\n" +
                            "Fri Nov 19 00:40:56 WET 2021\n" +
                            "Melhorias na GUI do Trello + GUI Inicial + GUI do GitHub\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Fri Nov 19 00:04:50 WET 2021\n" +
                            "Melhorias na GUI do Trello + GUI Inicial\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Thu Nov 18 22:49:19 WET 2021\n" +
                            "Melhorias na GUI do Trello\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Mon Nov 15 23:31:41 WET 2021\n" +
                            "Implementação da GUI do Trello e do GitHub\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Fri Oct 22 15:55:38 WEST 2021\n" +
                            "novo update da GUI inicial\n" +
                            "\n" +"paamt-iscte\n" +
                            "Fri Oct 22 15:47:34 WEST 2021\n" +
                            "novo update da GUI inicial\n" +
                            "\n" +"paamt-iscte\n" +
                            "Mon Oct 18 19:38:22 WEST 2021\n" +
                            "msg\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Mon Oct 18 19:35:55 WEST 2021\n" +
                            "msg\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Mon Oct 18 19:35:09 WEST 2021\n" +
                            "Merge branch 'PAAMT'\n" +
                            "\n" +
                            "# Conflicts:\n" +
                            "#\t.idea/misc.xml\n" +
                            "#\t.idea/vcs.xml\n" +
                            "#\tteste\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Mon Oct 18 19:30:11 WEST 2021\n" +
                            "msg\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Sat Oct 16 18:56:29 WEST 2021\n" +
                            "Merge remote-tracking branch 'origin/master'\n" +
                            "\n" +
                            "# Conflicts:\n" +
                            "#\tteste\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Sat Oct 16 18:53:02 WEST 2021\n" +
                            "msg\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Sat Oct 16 18:44:20 WEST 2021\n" +
                            "msg\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Fri Oct 15 17:29:14 WEST 2021\n" +
                            "z\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Fri Oct 15 17:17:14 WEST 2021\n" +
                            "z\n" +
                            "\n" +"paamt-iscte\n" +
                            "Fri Oct 15 16:27:19 WEST 2021\n" +
                            "z\n" +
                            "\n" +
                            "miguelrato18\n" +
                            "Fri Oct 15 16:22:25 WEST 2021\n" +
                            "test\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Fri Oct 15 16:18:46 WEST 2021\n" +
                            "z\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Fri Oct 15 15:37:16 WEST 2021\n" +
                            "z\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Fri Oct 15 15:24:26 WEST 2021\n" +
                            "z\n" +
                            "\n" +"paamt-iscte\n" +
                            "Thu Oct 14 20:59:47 WEST 2021\n" +
                            "msg\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Thu Oct 14 20:45:12 WEST 2021\n" +
                            "msg\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Thu Oct 14 20:30:24 WEST 2021\n" +
                            "msg\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Thu Oct 14 20:28:03 WEST 2021\n" +
                            "msg\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Thu Oct 14 20:21:50 WEST 2021\n" +
                            "msg\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Thu Oct 14 20:17:08 WEST 2021\n" +
                            "msg\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Thu Oct 14 20:14:12 WEST 2021\n" +
                            "msg\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Thu Oct 14 20:11:53 WEST 2021\n" +
                            "msg\n" +
                            "\n" +
                            "paamt-iscte\n" +
                            "Wed Oct 13 21:57:18 WEST 2021\n" +
                            "msg\n" +
                            "\n");
        for(int i=0;i<members.size();i++){
            Assertions.assertEquals(commitsByMember.get(i), github.getCommitInfoByMember(members.get(i)));
        }
    }

    @Test
    public void getCommitInfoTest() throws IOException {
        String s = "paamt-iscte\n" +
                                     "Fri Dec 03 15:49:57 WET 2021\n" +
                                     "Update README.md\n" +
                                     "\n" +
                                     "fasgs1-iscte\n" +
                                     "Wed Dec 01 19:03:28 WET 2021\n" +
                                     "Merge remote-tracking branch 'origin/master'\n" +
                                     "\n" +
                                     "fasgs1-iscte\n" +
                                     "Wed Dec 01 19:03:00 WET 2021\n" +
                                     "Finalização da TrelloAPI\n" +
                                     "\n" +
                                     "fasgs1-iscte\n" +
                                     "Wed Dec 01 18:59:19 WET 2021\n" +
                                     "Merge remote-tracking branch 'origin/fasgs1'\n" +
                                     "\n" +
                                     "# Conflicts:\n" +
                                     "#\t.idea/workspace.xml\n" +
                                     "#\ttarget/classes/Main.class\n" +
                                     "\n" +
                                     "fasgs1-iscte\n" +
                                     "Wed Dec 01 18:57:33 WET 2021\n" +
                                     "Finalização da TrelloAPI\n" +
                                     "\n" +
                                     "fasgs1-iscte\n" +
                                     "Wed Dec 01 15:31:35 WET 2021\n" +
                                     "Update do Trello Api\n" +
                                     "\n" +
                                     "fasgs1-iscte\n" +
                                     "Wed Dec 01 13:54:42 WET 2021\n" +
                                     "Update do Trello Api\n" +
                                     "\n" +
                                     "fasgs1-iscte\n" +
                                     "Wed Dec 01 11:29:59 WET 2021\n" +
                                     "Update do Trello Api\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Wed Dec 01 00:31:11 WET 2021\n" +
                                     "Update README.md\n" +
                                     "\n" +
                                     "fasgs1-iscte\n" +
                                     "Fri Nov 26 15:57:09 WET 2021\n" +
                                     "Update do Trello Api\n" +
                                     "\n" +
                                     "fasgs1-iscte\n" +
                                     "Fri Nov 26 15:48:30 WET 2021\n" +
                                     "Merge remote-tracking branch 'origin/Rato' into fasgs1\n" +
                                     "\n" +
                                     "# Conflicts:\n" +
                                     "#\t.idea/workspace.xml\n" +
                                     "#\ttarget/classes/Main.class\n" +
                                     "\n" +
                                     "fasgs1-iscte\n" +
                                     "Fri Nov 26 15:45:03 WET 2021\n" +
                                     "Merge remote-tracking branch 'origin/PAAMT' into fasgs1\n" +
                                     "\n" +
                                     "# Conflicts:\n" +
                                     "#\t.idea/libraries/Maven__commons_codec_commons_codec_1_9.xml\n" +
                                     "#\t.idea/libraries/Maven__org_apache_httpcomponents_httpasyncclient_4_1_1.xml\n" +
                                     "#\t.idea/libraries/Maven__org_apache_httpcomponents_httpclient_4_5_2.xml\n" +
                                     "#\t.idea/libraries/Maven__org_apache_httpcomponents_httpcore_4_4_4.xml\n" +
                                     "#\t.idea/libraries/Maven__org_apache_httpcomponents_httpcore_nio_4_4_4.xml\n" +
                                     "#\t.idea/libraries/Maven__org_apache_httpcomponents_httpmime_4_5_2.xml\n" +
                                     "#\t.idea/workspace.xml\n" +
                                     "#\tProject.iml\n" +
                                     "#\tpom.xml\n" +
                                     "#\tsrc/main/java/Main.java\n" +
                                     "#\tsrc/main/java/TrelloAPI.java\n" +
                                     "#\ttarget/classes/Main.class\n" +
                                     "#\ttarget/classes/TrelloAPI.class\n" +
                                     "\n" +
                                     "miguelrato18\n" +
                                     "Fri Nov 26 15:04:52 WET 2021\n" +
                                     "Maven com API GitHub\n" +
                                     "\n" +
                                     "miguelrato18\n" +
                                     "Fri Nov 26 14:59:19 WET 2021\n" +
                                     "Merge remote-tracking branch 'origin/PAAMT' into Rato\n" +
                                     "\n" +
                                     "# Conflicts:\n" +
                                     "#\t.idea/compiler.xml\n" +
                                     "#\t.idea/workspace.xml\n" +
                                     "#\tProject.iml\n" +
                                     "#\ttarget/classes/GUI_Trello.class\n" +
                                     "\n" +
                                     "miguelrato18\n" +
                                     "Mon Nov 22 14:46:15 WET 2021\n" +
                                     "GUI de acesso a github e trello desenvolvida(parcialmente)V2\n" +
                                     "\n" +
                                     "miguelrato18\n" +
                                     "Mon Nov 22 14:23:12 WET 2021\n" +
                                     "GUI de acesso a github e trello desenvolvida(parcialmente)\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Sun Nov 21 20:26:13 WET 2021\n" +
                                     "+Update\n" +
                                     "\n" +
                                     "fasgs1-iscte\n" +
                                     "Sun Nov 21 20:24:00 WET 2021\n" +
                                     "Update do Trello Api\n" +
                                     "\n" +
                                     "fasgs1-iscte\n" +
                                     "Sun Nov 21 20:23:16 WET 2021\n" +
                                     "Merge remote-tracking branch 'origin/fasgs1'\n" +
                                     "\n" +
                                     "# Conflicts:\n" +
                                     "#\t.idea/workspace.xml\n" +
                                     "#\tpom.xml\n" +
                                     "#\ttarget/classes/Main.class\n" +
                                     "\n" +
                                     "fasgs1-iscte\n" +
                                     "Sun Nov 21 20:22:41 WET 2021\n" +
                                     "Update do Trello Api\n" +
                                     "\n" +
                                     "fasgs1-iscte\n" +
                                     "Sun Nov 21 19:05:39 WET 2021\n" +
                                     "Update do Trello Api\n" +
                                     "\n" +
                                     "fasgs1-iscte\n" +
                                     "Sun Nov 21 19:05:18 WET 2021\n" +
                                     "Update do Trello Api\n" +
                                     "\n" +
                                     "fasgs1-iscte\n" +
                                     "Sun Nov 21 19:03:40 WET 2021\n" +
                                     "Update do Trello Api\n" +
                                     "\n" +
                                     "fasgs1-iscte\n" +
                                     "Sun Nov 21 18:52:55 WET 2021\n" +
                                     "Merge remote-tracking branch 'origin/fasgs1'\n" +
                                     "\n" +
                                     "# Conflicts:\n" +
                                     "#\tsrc/main/java/Main.java\n" +
                                     "#\ttarget/classes/GUI_Trello.class\n" +
                                     "#\ttarget/classes/Main.class\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Sun Nov 21 18:44:06 WET 2021\n" +
                                     "+update\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Sun Nov 21 18:43:59 WET 2021\n" +
                                     "+update\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Sun Nov 21 18:42:43 WET 2021\n" +
                                     "Merge remote-tracking branch 'origin/PAAMT'\n" +
                                     "\n" +
                                     "# Conflicts:\n" +
                                     "#\t.idea/compiler.xml\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Sun Nov 21 18:41:08 WET 2021\n" +
                                     "+update\n" +
                                     "\n" +
                                     "fasgs1-iscte\n" +
                                     "Sun Nov 21 18:38:08 WET 2021\n" +
                                     "Update do Trello Api\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Fri Nov 19 17:11:59 WET 2021\n" +
                                     "update\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Fri Nov 19 16:23:24 WET 2021\n" +
                                     "Novo update da GUI do trello/github:\n" +
                                     " +Adicionado o campo TrelloLogin [GUI_Trello];\n" +
                                     " +Adicionado o campo GithubLogin [GUI_Github];\n" +
                                     " +Adicionado Getters para token, key e user [GUI_Trello];\n" +
                                     " +Adicionado Getters para token, repositoryName, repositoryOwner [GUI_Github];\n" +
                                     "\n" +
                                     " +Update ao campo SetPassword [GUI_Trello/GUI_Github];\n" +
                                     " +Update ao actionPerformed do button_login [GUI_Trello/GUI_GitHub];\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Fri Nov 19 16:22:31 WET 2021\n" +
                                     "Novo update da GUI do trello/github:\n" +
                                     " +Adicionado o campo TrelloLogin [GUI_Trello];\n" +
                                     " +Adicionado o campo GithubLogin [GUI_Github];\n" +
                                     " +Adicionado Getters para token, key e user [GUI_Trello];\n" +
                                     " +Adicionado Getters para token, repositoryName, repositoryOwner [GUI_Github];\n" +
                                     "\n" +
                                     " +Update ao campo SetPassword [GUI_Trello/GUI_Github];\n" +
                                     " +Update ao actionPerformed do button_login [GUI_Trello/GUI_GitHub];\n" +
                                     "\n" +
                                     "miguelrato18\n" +
                                     "Fri Nov 19 15:24:06 WET 2021\n" +
                                     "update para ficar com mesma versao de colegas\n" +
                                     "\n" +
                                     "miguelrato18\n" +
                                     "Fri Nov 19 15:16:40 WET 2021\n" +
                                     "Merge remote-tracking branch 'origin/PAAMT' into Rato\n" +
                                     "\n" +
                                     "fasgs1-iscte\n" +
                                     "Fri Nov 19 15:16:39 WET 2021\n" +
                                     "Merge remote-tracking branch 'origin/PAAMT' into fasgs1\n" +
                                     "\n" +
                                     "# Conflicts:\n" +
                                     "#\t.idea/compiler.xml\n" +
                                     "#\tProject.iml\n" +
                                     "\n" +
                                     "miguelrato18\n" +
                                     "Fri Nov 19 15:16:33 WET 2021\n" +
                                     "github button works\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Fri Nov 19 15:15:08 WET 2021\n" +
                                     "novo update da GUI do trello\n" +
                                     "+Adicionado o campo User para a API\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Fri Nov 19 15:13:26 WET 2021\n" +
                                     "novo update da GUI do trello\n" +
                                     "+Adicionado o campo User para a API\n" +
                                     "\n" +
                                     "fasgs1-iscte\n" +
                                     "Fri Nov 19 14:48:20 WET 2021\n" +
                                     "Update do pom.xml com as dependencias necessarias e começo da implepentação da Api do Trelloo\n" +
                                     "\n" +
                                     "miguelrato18\n" +
                                     "Fri Nov 19 14:11:37 WET 2021\n" +
                                     "Merge remote-tracking branch 'origin/PAAMT' into Rato\n" +
                                     "\n" +
                                     "# Conflicts:\n" +
                                     "#\t.idea/compiler.xml\n" +
                                     "#\tProject.iml\n" +
                                     "#\tsrc/main/java/GUI.form\n" +
                                     "#\tsrc/main/java/GUI.java\n" +
                                     "#\ttarget/classes/GUI$1.class\n" +
                                     "#\ttarget/classes/GUI.class\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Fri Nov 19 00:40:56 WET 2021\n" +
                                     "Melhorias na GUI do Trello + GUI Inicial + GUI do GitHub\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Fri Nov 19 00:04:50 WET 2021\n" +
                                     "Melhorias na GUI do Trello + GUI Inicial\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Thu Nov 18 22:49:19 WET 2021\n" +
                                     "Melhorias na GUI do Trello\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Mon Nov 15 23:31:41 WET 2021\n" +
                                     "Implementação da GUI do Trello e do GitHub\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Fri Oct 22 15:55:38 WEST 2021\n" +
                                     "novo update da GUI inicial\n" +
                                     "\n" +
                                     "miguelrato18\n" +
                                     "Fri Oct 22 15:49:03 WEST 2021\n" +
                                     "github button works\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Fri Oct 22 15:47:34 WEST 2021\n" +
                                     "novo update da GUI inicial\n" +
                                     "\n" +
                                     "miguelrato18\n" +
                                     "Fri Oct 22 15:42:25 WEST 2021\n" +
                                     "msg\n" +
                                     "\n" +
                                     "miguelrato18\n" +
                                     "Fri Oct 22 14:51:54 WEST 2021\n" +
                                     "msg\n" +
                                     "\n" +
                                     "miguelrato18\n" +
                                     "Fri Oct 22 14:50:23 WEST 2021\n" +
                                     "Merge branch 'master' into Rato\n" +
                                     "\n" +
                                     "# Conflicts:\n" +
                                     "#\t.idea/ES-LETI-1Sem-2021-Grupo11.iml\n" +
                                     "#\t.idea/misc.xml\n" +
                                     "#\tteste\n" +
                                     "\n" +
                                     "miguelrato18\n" +
                                     "Fri Oct 22 14:48:52 WEST 2021\n" +
                                     "msg\n" +
                                     "\n" +
                                     "miguelrato18\n" +
                                     "Fri Oct 22 14:37:13 WEST 2021\n" +
                                     "Merge remote-tracking branch 'origin/master' into Rato\n" +
                                     "\n" +
                                     "# Conflicts:\n" +
                                     "#\tteste\n" +
                                     "\n" +
                                     "fasgs1-iscte\n" +
                                     "Tue Oct 19 14:02:19 WEST 2021\n" +
                                     "Create README.md\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Mon Oct 18 19:38:22 WEST 2021\n" +
                                     "msg\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Mon Oct 18 19:35:55 WEST 2021\n" +
                                     "msg\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Mon Oct 18 19:35:09 WEST 2021\n" +
                                     "Merge branch 'PAAMT'\n" +
                                     "\n" +
                                     "# Conflicts:\n" +
                                     "#\t.idea/misc.xml\n" +
                                     "#\t.idea/vcs.xml\n" +
                                     "#\tteste\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Mon Oct 18 19:30:11 WEST 2021\n" +
                                     "msg\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Sat Oct 16 18:56:29 WEST 2021\n" +
                                     "Merge remote-tracking branch 'origin/master'\n" +
                                     "\n" +
                                     "# Conflicts:\n" +
                                     "#\tteste\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Sat Oct 16 18:53:02 WEST 2021\n" +
                                     "msg\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Sat Oct 16 18:44:20 WEST 2021\n" +
                                     "msg\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Fri Oct 15 17:29:14 WEST 2021\n" +
                                     "z\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Fri Oct 15 17:17:14 WEST 2021\n" +
                                     "z\n" +
                                     "\n" +
                                     "miguelrato18\n" +
                                     "Fri Oct 15 16:32:39 WEST 2021\n" +
                                     "test\n" +
                                     "\n" +
                                     "miguelrato18\n" +
                                     "Fri Oct 15 16:28:10 WEST 2021\n" +
                                     "Merge branch 'PAAMT' into Rato\n" +
                                     "\n" +
                                     "# Conflicts:\n" +
                                     "#\t.idea/ES-LETI-1Sem-2021-Grupo11.iml\n" +
                                     "#\t.idea/misc.xml\n" +
                                     "#\tteste\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Fri Oct 15 16:27:19 WEST 2021\n" +
                                     "z\n" +
                                     "\n" +
                                     "miguelrato18\n" +
                                     "Fri Oct 15 16:22:25 WEST 2021\n" +
                                     "test\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Fri Oct 15 16:18:46 WEST 2021\n" +
                                     "z\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Fri Oct 15 15:37:16 WEST 2021\n" +
                                     "z\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Fri Oct 15 15:24:26 WEST 2021\n" +
                                     "z\n" +
                                     "\n" +
                                     "fasgs1-iscte\n" +
                                     "Fri Oct 15 14:45:29 WEST 2021\n" +
                                     "ifrcdkms xfrecd\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Thu Oct 14 20:59:47 WEST 2021\n" +
                                     "msg\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Thu Oct 14 20:45:12 WEST 2021\n" +
                                     "msg\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Thu Oct 14 20:30:24 WEST 2021\n" +
                                     "msg\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Thu Oct 14 20:28:03 WEST 2021\n" +
                                     "msg\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Thu Oct 14 20:21:50 WEST 2021\n" +
                                     "msg\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Thu Oct 14 20:17:08 WEST 2021\n" +
                                     "msg\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Thu Oct 14 20:14:12 WEST 2021\n" +
                                     "msg\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Thu Oct 14 20:11:53 WEST 2021\n" +
                                     "msg\n" +
                                     "\n" +
                                     "paamt-iscte\n" +
                                     "Wed Oct 13 21:57:18 WEST 2021\n" +
                                     "msg\n" +
                                     "\n" +
                                     "miguelrato18\n" +
                                     "Wed Oct 13 16:59:52 WEST 2021\n" +
                                     "test\n" +
                                     "\n" +
                                     "miguelrato18\n" +
                                     "Wed Oct 13 16:52:16 WEST 2021\n" +
                                     "test\n" +
                                     "\n" +
                                     "miguelrato18\n" +
                                     "Wed Oct 13 16:50:59 WEST 2021\n" +
                                     "test\n" +
                                     "\n" +
                                     "miguelrato18\n" +
                                     "Wed Oct 13 16:50:19 WEST 2021\n" +
                                     "test\n" +
                                     "\n" +
                                     "miguelrato18\n" +
                                     "Wed Oct 13 16:49:45 WEST 2021\n" +
                                     "test\n" +
                                     "\n" +
                                     "DanielMoreiraCampos\n" +
                                     "Sat Oct 09 18:18:56 WEST 2021\n" +
                                     "Mensagem sobre o estado atual deste projeto\n" +
                                     "\n" +
                                     "miguelrato18\n" +
                                     "Sat Oct 09 18:05:28 WEST 2021\n" +
                                     "status message\n" +
                                     "\n";
        Assertions.assertEquals(s, github.getCommitInfo());
    }

    @Test
    public void getTagstest() throws IOException {
        List<String> tags = new ArrayList<>();
        tags.add("test");

        List<GHTag> githubtag = github.getTags();
        for(int i=0; i<githubtag.size();i++){
            Assertions.assertEquals(tags.get(i),githubtag.get(i).getName());
        }

    }

}