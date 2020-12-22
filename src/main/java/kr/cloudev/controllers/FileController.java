package kr.cloudev.controllers;

import com.google.gson.GsonBuilder;
import kr.cloudev.models.action.FileListModel;
import kr.cloudev.models.action.RepoListModel;
import kr.cloudev.models.action.RepositoryModel;
import kr.cloudev.models.view.BaseModel;
import kr.cloudev.models.view.page.RepoModel;
import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHMyself;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SuppressWarnings("DuplicatedCode")
@Controller
@RequestMapping("/file")
public class FileController {

    @ResponseBody
    @PostMapping("/list.do")
    public String doFileList(HttpServletRequest request,
                             @RequestParam Map<String, Object> param) throws IOException {
        HttpSession session = request.getSession();

        GitHub github = (GitHub) session.getAttribute("github");

        if (github == null || param == null) {
            return null;
        }

        String repoName = (String) param.get("repoName");
        String path = (String) param.get("path");

        GHMyself user = (GHMyself) session.getAttribute("user");
        GHRepository repo = user.getRepository(repoName);

        List<FileListModel> fileList = new ArrayList<>();
        List<GHContent> directoryContent = repo.getDirectoryContent(path);

        for (GHContent content : directoryContent) {
            fileList.add(new FileListModel(content));
        }

        return new GsonBuilder().create().toJson(fileList);
    }


    @ResponseBody
    @PostMapping("/content.do")
    public String doFileContent(HttpServletRequest request,
                         @RequestParam Map<String, Object> param) throws IOException {
        HttpSession session = request.getSession();

        GitHub github = (GitHub) session.getAttribute("github");

        if (github == null || param == null) {
            return null;
        }

        String repoName = (String) param.get("repoName");
        String fileName = (String) param.get("fileName");

        GHMyself user = (GHMyself) session.getAttribute("user");
        GHRepository repo = user.getRepository(repoName);
        GHContent content = repo.getFileContent(fileName);
        String contents = new String(content.read().readAllBytes(), StandardCharsets.UTF_8);

        return new GsonBuilder().create().toJson(contents);
    }
}
