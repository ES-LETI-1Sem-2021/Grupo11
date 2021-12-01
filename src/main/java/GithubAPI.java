import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import java.io.IOException;
import java.util.List;

public class GithubAPI {

    private static int NrActivityWArt;
    private static int NrActivityWOArt;
    private static String Readme;
    private static List<String> TagList;
    private static List<String[]> CommitInfo;
    private static List<String[]> MemberInfo;
    private GitHub github;


    public GithubAPI(String password_token_text) throws IOException {
        github = new GitHubBuilder().withOAuthToken(password_token_text).build();


    }
//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


    public void getProjectDiscription() throws IOException {
         GHRepository GitRepo = github.getRepository("miguelrato18/ES-LETI-1Sem-2021-Grupo11");


        //nomes de que ta no project
        System.out.println(GitRepo.getCollaboratorNames().toString());
        //ficheiro readme
        System.out.println(GitRepo.getFileContent("README.md").getContent());
        //etiquetas
        System.out.println(GitRepo.listTags());
        //commits
        for (int i = 0; i < GitRepo.listCommits().toList().size();i++){
            System.out.println(GitRepo.listCommits().toList().get(i).getCommitShortInfo().getAuthor().getName());
            System.out.println(GitRepo.listCommits().toList().get(i).getCommitShortInfo().getCommitDate());
            System.out.println(GitRepo.listCommits().toList().get(i).getCommitShortInfo().getMessage());
        }


    }

}
