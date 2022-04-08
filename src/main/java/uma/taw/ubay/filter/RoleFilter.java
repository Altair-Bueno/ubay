package uma.taw.ubay.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uma.taw.ubay.SessionKeys;
import uma.taw.ubay.entity.KindEnum;
import uma.taw.ubay.entity.LoginCredentialsEntity;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RoleFilter extends HttpFilter {

    private List<KindEnum> role;

    @Override
    public void init(FilterConfig config) throws ServletException {
        String roleParameter = config.getInitParameter("role");
        if (roleParameter == null) throw new ServletException("RoleFilter requires a `role` parameter");

        role = Arrays.stream(roleParameter.split("[;]"))
                .map(KindEnum::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        // Caution: This might trigger Safari's cookie privacy policy
        HttpSession session = req.getSession();
        LoginCredentialsEntity credentialsEntity = (LoginCredentialsEntity) session.getAttribute(SessionKeys.LOGIN_CREDENTIALS);
        KindEnum sessionKind = credentialsEntity.getKind();

        if (role.stream().anyMatch(x->x.equals(sessionKind))) {
            chain.doFilter(req,res);
        } else {
            throw new RuntimeException("The role kind [" + sessionKind + "] is not allowed for this request");
        }
    }
}
