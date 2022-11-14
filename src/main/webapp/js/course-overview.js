const renderCourse = () => {
    const id = "courses"
    clearElement(id)
    course.forEach((course) => {
        const tr = document.createElement("tr")
        const tdname = document.createElement("td")
        const name = document.createTextNode( `${course.name}` )
        tdname.appendChild(name)
        const tdlectors = document.createElement("td")
        const lectors = document.createTextNode(`${course.lectors}`)
        tdlectors.appendChild(lectors)
        tr.appendChild(tdname)
        tr.appendChild(tdlectors)
        document.getElementById(id).appendChild(tr)
    })
}

renderCourse()