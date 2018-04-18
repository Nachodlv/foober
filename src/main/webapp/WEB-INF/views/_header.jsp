<div class="container" style="margin-bottom: -3rem">
    <div class="dropdown" align="right">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fas fa-bars"></i>
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a>
            <a class="dropdown-item" onclick="window.location = '${pageContext.request.contextPath}' + '/${loginedUser.role}'.toLowerCase() + 'Info'">Edit profile</a>
        </div>
    </div>
</div>
