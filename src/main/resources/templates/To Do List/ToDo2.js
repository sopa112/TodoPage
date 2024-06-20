const input = document.querySelector("input");
const addBtn = document.querySelector(".btn-add");
const ul = document.querySelector("ul");
const empty = document.querySelector(".empty");

// Obtener el elemento donde mostrar la fecha actual
const currentDateElement = document.getElementById("current-date");

// Obtener la fecha actual
const currentDate = new Date();

// Formatear la fecha como deseado (por ejemplo, "dd/mm/aaaa")
const formattedDate = currentDate.toLocaleDateString("es-ES");

// Mostrar la fecha en el elemento HTML
currentDateElement.textContent = formattedDate;

addBtn.addEventListener("click", (e) => {
    e.preventDefault();

    const text = input.value;

    if (text !== "") {
        const li = document.createElement("li");
        const p = document.createElement("p");
        p.textContent = text;

        li.appendChild(p);
        li.appendChild(addDeleteBtn());
        ul.appendChild(li);

        input.value = "";
        empty.style.display = "none";
    }
});

function addDeleteBtn() {
    const deleteBtn = document.createElement("button");

    deleteBtn.textContent = "🗑️";
    deleteBtn.className = "btn-delete";

    deleteBtn.addEventListener("click", (e) => {
        const item = e.target.parentElement;
        ul.removeChild(item);

        const items = document.querySelectorAll("li");

        if (items.length === 0) {
            empty.style.display = "block";
        }
    });

    return deleteBtn;
}