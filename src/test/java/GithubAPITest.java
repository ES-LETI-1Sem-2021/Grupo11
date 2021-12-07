import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kohsuke.github.GHTag;

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

    //TODO TESTS para as funções:getCommitInfo e getCommitInfoByMember

    @Test
    public void getTags() throws IOException {
        List<String> tags = new ArrayList<>();
        tags.add("test");

        List<GHTag> githubtag = github.getTags();
        for(int i=0; i<githubtag.size();i++){
            Assertions.assertEquals(tags.get(i),githubtag.get(i).getName());
        }

    }





}