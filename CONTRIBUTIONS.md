# [Altair Bueno](https://github.com/Altair-Bueno/ubay/commits/master?author=Altair-Bueno)

- Filters
  - [AuthFilter](src/main/java/uma/taw/ubay/filter/AuthFilter.java)
  - [RoleFilter](src/main/java/uma/taw/ubay/filter/RoleFilter.java)
- JSP
  - [login.jsp](src/main/webapp/auth/login.jsp)
  - [register.jsp](src/main/webapp/auth/register.jsp)
  - [Vendor bids](src/main/webapp/vendor/bids/index.jsp)
  - [User bids](src/main/webapp/users/bids/index.jsp)
  - [resetPassword.jsp](src/main/webapp/auth/resetPassword.jsp)
  - [changePassword.jsp](src/main/webapp/auth/changePassword.jsp)
- Servlets
  - [Login](src/main/java/uma/taw/ubay/servlet/auth/Login.java)
  - [Register](src/main/java/uma/taw/ubay/servlet/auth/Register.java)
  - [Vendor Bids](src/main/java/uma/taw/ubay/servlet/vendor/bids/Index.java)
  - [User Bids](src/main/java/uma/taw/ubay/servlet/users/bids/Index.java)
  - [Create a new bid](src/main/java/uma/taw/ubay/servlet/users/bids/New.java)
  - [Password reset](src/main/java/uma/taw/ubay/servlet/auth/ResetPassword.java)
  - [Change password](src/main/java/uma/taw/ubay/servlet/auth/ChangePassword.java)
  - [Sign off](src/main/java/uma/taw/ubay/servlet/auth/SignOff.java)
  - [Index redirect](src/main/java/uma/taw/ubay/servlet/Index.java)
  - [Image](src/main/java/uma/taw/ubay/servlet/Image.java)
- Services
  - [AuthService](src/main/java/uma/taw/ubay/service/AuthService.java)
  - [BidService](src/main/java/uma/taw/ubay/service/BidService.java)
- Keyfiles
  - [AuthKeys](src/main/java/uma/taw/ubay/AuthKeys.java)
  - [SessionKeys](src/main/java/uma/taw/ubay/SessionKeys.java)
  - [UsersKeys](src/main/java/uma/taw/ubay/UsersKeys.java)
  - [VendorKeys](src/main/java/uma/taw/ubay/VendorKeys.java)
- Facades
  - [MinioFacade](src/main/java/uma/taw/ubay/dao/MinioFacade.java)
- Docker
  - [Docker compose](docker-compose.yml)
  - [Ubay's Dockerfile](Dockerfile)
- Other
  - [GitHub actions workflows](.github/workflows)
  - Project setup (Maven, GlassFish, Minio, Authentication...)
  - [README Instructions](README.md)
  - Database design & ORM
  - [Custom error pages](src/main/webapp/WEB-INF/error)
  - Other minor changes

# [Fran Hernandez](https://github.com/Altair-Bueno/ubay/commits/master?author=fran1215)

# [José Luis Bueno](https://github.com/Altair-Bueno/ubay/commits/master?author=jxtaaa)

- DB Design
- Admin functions on user page
  - [/users/delete](src/main/java/uma/taw/ubay/servlet/users/Delete.java)
  - [/users/modify](src/main/java/uma/taw/ubay/servlet/users/Modify.java)
  - [/users/](src/main/java/uma/taw/ubay/servlet/users/Users.java)
- User filters
  - [ClientFacade](src/main/java/uma/taw/ubay/dao/ClientFacade.java)
