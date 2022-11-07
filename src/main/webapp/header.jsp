<header>
    <h1>
        <span>Company App</span>
    </h1>
    <nav>
        <ul>
            <li ${param.actual eq 'Home'?"id = actual":""}><a href="Controller">Home</a></li>
            <li ${param.actual eq 'Users'?"id = actual":""}><a href="Controller?command=Overview">Users</a></li>
            <li ${param.actual eq 'Register'?"id = actual":""}><a href="Controller?command=Register">Register</a></li>
            <li ${param.actual eq 'Projects'?"id = actual":""}><a href="Controller?command=ProjectOverview">Projects</a></li>
            <li ${param.actual eq 'Add Project'?"id = actual":""}><a href="Controller?command=RegisterP">Add Project</a></li>
            <li ${param.actual eq 'Work Orders'?"id = actual":""}><a href="Controller?command=OrderOverview">Work Orders</a></li>
            <li ${param.actual eq 'Add Work Order'?"id = actual":""}><a href="Controller?command=RegisterO">Add Work Order</a></li>
        </ul>
    </nav>
</header>
<%--<li><a href="FilmServlet?page=index" ${param.current.equals("index")?"class='here'":""}>Home</a></li>--%>
<%--<li id="actual"><a href="Controller">Home</a></li>--%>