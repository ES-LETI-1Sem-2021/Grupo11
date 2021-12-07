import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import org.kohsuke.github.*;
import javax.swing.*;
import java.awt.*;
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
     */
    public String getREADME() throws IOException {
        Readme = GitRepo.getFileContent("README.md").getContent();
    return Readme;
    }

    /**
     * Devolve numa String toda a informação sobre os commits no repositório.
     *
     * @return - uma String;
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
     * Devolve numa String toda a informação sobre os commits no repositório do autor fornecido.
     *
     * @param autor - Objecto do tipo GHUser;
     * @return - uma String;
     */
    public String getCommitInfoByMember(GHUser autor) throws IOException {
        String commitInfo = "";
        for (int i = 0; i < GitRepo.listCommits().toList().size();i++) {
            if(GitRepo.listCommits().toList().get(i).getCommitShortInfo().getAuthor().getName().equals(autor.getName())) {
                commitInfo += GitRepo.listCommits().toList().get(i).getCommitShortInfo().getAuthor().getName() + ",\n"
                        + GitRepo.listCommits().toList().get(i).getCommitShortInfo().getCommitDate() + ",\n"
                        + GitRepo.listCommits().toList().get(i).getCommitShortInfo().getMessage() + "\n\n";
            }
        }
        return commitInfo;
    }


    /**
     * Devolve uma lista de objectos do tipo GHTag.
     *
     * @return - uma List de GHTag {@link GHTag};
     */
    public List<GHTag> getTags() throws IOException {
        tagList = GitRepo.listTags().toList();
        return tagList;
    }




}
