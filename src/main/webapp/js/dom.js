const clearElement = (id) => {
    const element = document.getElementById(id)
    element.innerHTML = "<caption>PBA TI</caption>\n" +
        "            <tr>\n" +
        "                <th>Name</th>\n" +
        "                <th>Lectors</th>\n" +
        "            </tr>"
}