import org.kohsuke.github.*;

import java.io.IOException;
import java.util.List;

/**
 * Classe que que contém os métodos para obter objetos do Github
 */
public class GithubAPI {

    private static String Readme;
    private static List<GHTag> TagList;
    private final GitHub github;
    GHRepository GitRepo;

    public GithubAPI(String token,String repositoryOwner,String repositoryName) throws IOException {
        github = new GitHubBuilder().withOAuthToken(token).build();
        GitRepo = github.getRepository(repositoryOwner + "/" + repositoryName);

    }


    /**
     * Devolve numa String o conteudo do ficheiro README.md presente no repositório GitHub
     *
     * @return - uma String;
     */
    public String getREADME() throws IOException {
        Readme = GitRepo.getFileContent("README.md").getContent();
    return Readme;
    }


    /**
     * Devolve numa String toda a informação sobre os commits no repositório do autor fornecido
     *
     * @return - uma String;
     */
    public String getCommitInfo(GHUser autor) throws IOException {
        String commitInfo = "";
        for (int i = 0; i < GitRepo.listCommits().toList().size();i++) {
            if(GitRepo.listCommits().toList().get(i).getCommitShortInfo().getAuthor().equals(autor)) {
                commitInfo += GitRepo.listCommits().toList().get(i).getCommitShortInfo().getAuthor().getName() + ",\n"
                        + GitRepo.listCommits().toList().get(i).getCommitShortInfo().getCommitDate() + ",\n"
                        + GitRepo.listCommits().toList().get(i).getCommitShortInfo().getMessage() + "\n\n";
            }
        }
        return commitInfo;
    }


    /**
     * Devolve uma lista de objectos do tipo GHTag
     *
     * @return - uma List de GHTag
     */
    public List<GHTag> getTags() throws IOException {
        TagList = GitRepo.listTags().toList();
        return TagList;
    }



}
