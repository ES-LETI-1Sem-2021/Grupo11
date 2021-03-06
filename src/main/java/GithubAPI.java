import org.kohsuke.github.*;

import java.io.IOException;
import java.util.List;

/**
 * Classe que que contém os métodos para obter objetos do Github.
 */
public class GithubAPI {

    private static String Readme;
    private static List<GHTag> tagList;
    private final GitHub github;
    GHRepository GitRepo;

    public GithubAPI(String token,String repositoryOwner,String repositoryName) throws IOException {
        github = new GitHubBuilder().withOAuthToken(token).build();
        GitRepo = github.getRepository(repositoryOwner + "/" + repositoryName);

    }


    /**
     * Devolve numa String o conteudo do ficheiro README.md presente no repositório GitHub.
     *
     * @return - uma String;
     * @throws IOException;
     */
    public String getREADME() throws IOException {
        Readme = GitRepo.getFileContent("README.md").getContent();
    return Readme;
    }

    /**
     * Devolve numa String toda a informação sobre os commits no repositório.
     *
     * @return - uma String;
     * @throws IOException;
     */
    public String getCommitInfo() throws IOException {
        String commitInfo = "";
        for (int i = 0; i < GitRepo.listCommits().toList().size();i++) {
            commitInfo += GitRepo.listCommits().toList().get(i).getCommitShortInfo().getAuthor().getName() + "\n"
                          + GitRepo.listCommits().toList().get(i).getCommitShortInfo().getCommitDate() + "\n"
                          + GitRepo.listCommits().toList().get(i).getCommitShortInfo().getMessage() + "\n\n";
        }
        return commitInfo;
    }


    /**
     * Devolve uma lista de objectos do tipo GHTag.
     *
     * @return - uma List de GHTag {@link GHTag};
     * @throws IOException;
     */
    public List<GHTag> getTags() throws IOException {
        tagList = GitRepo.listTags().toList();
        return tagList;
    }

}
