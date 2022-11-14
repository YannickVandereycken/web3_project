const renderAnimal = () => {
    const id = "courses"
    clearElement(id)
    course.forEach((course) => {
        const p = document.createElement("p")
        const text = document.createTextNode(`${course.name} 
                   is een ${course.type.toLocaleLowerCase()} van 
                   ${course.age} jaar oud.`)
        p.appendChild(text)
        document.getElementById(id).appendChild(p)
    })
}

renderAnimal()