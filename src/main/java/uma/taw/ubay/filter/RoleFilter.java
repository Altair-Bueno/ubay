package uma.taw.ubay.filter;

import jakarta.ejb.EJB;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uma.taw.ubay.SessionKeys;
import uma.taw.ubay.dao.LoginCredentialsFacade;
import uma.taw.ubay.dto.LoginDTO;
import uma.taw.ubay.entity.KindEnum;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RoleFilter extends HttpFilter {
    @EJB
    LoginCredentialsFacade loginCredentialsFacade;

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
        var loginDTO = (LoginDTO) session.getAttribute(SessionKeys.LOGIN_DTO);
        var loginCredentials= loginCredentialsFacade.find(loginDTO.getUsername());
        KindEnum sessionKind = loginCredentials.getKind();

        boolean matches = role.stream().anyMatch(x->x.equals(sessionKind));
        if (matches) {
            chain.doFilter(req,res);
        } else {
            throw new RuntimeException("The role kind [" + sessionKind + "] is not allowed for this request");
        }
    }
}
