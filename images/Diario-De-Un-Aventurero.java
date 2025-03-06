import java.time.LocalDateTime;

/*** Clase que representa una misión en el diario aventurero*/
class Mision {
    private String descripcion;
    private LocalDateTime fechaCompletado;

    public Mision(String descripcion) {
        this.descripcion = descripcion;
        this.fechaCompletado = LocalDateTime.now();
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Mision) {
            Mision otraMision = (Mision) obj;
            return this.descripcion.equals(otraMision.getDescripcion());
        }
        return false;
    }

    @Override
    public String toString() {
        return "Misión: " + descripcion + " (Completada: " + fechaCompletado + ")";
    }
}

/*Clase que implementa el DiarioAventurero usando una estructura tipo Pila*/
class DiarioAventurero {
    private Node cabeza;
    private int tamano;

    private class Node {
        Mision mision;
        Node siguiente;

        Node(Mision mision) {
            this.mision = mision;
            this.siguiente = null;
        }
    }

    /*Constructor que inicializa el diario vacío*/
    public DiarioAventurero() {
        cabeza = null;
        tamano = 0;
    }

    /*** Registra una nueva misión en el diario
     * @param descripcion descripción de la misión completada*/
    public void registrarMision(String descripcion) {
        Mision mision = new Mision(descripcion);
        Node nuevoNodo = new Node(mision);
        nuevoNodo.siguiente = cabeza;
        cabeza = nuevoNodo;
        tamano++;
    }

    /*** Elimina la última misión registrada si resultó fallida
     * @return true si se eliminó la misión, false si el diario está vacío*/
    public boolean eliminarUltimaMision() {
        if (cabeza == null) {
            return false;
        }
        cabeza = cabeza.siguiente;
        tamano--;
        return true;
    }

    /* Muestra todas las misiones en orden de finalización más reciente*/
    public void mostrarMisiones() {
        Node actual = cabeza;
        while (actual != null) {
            System.out.println(actual.mision.toString());
            actual = actual.siguiente;
        }
    }

    /*** Busca si una misión específica ya fue completada
     * @param descripcion descripción de la misión a buscar
     * @return true si la misión fue encontrada, false en caso contrario*/
    public boolean buscarMision(String descripcion) {
        Node actual = cabeza;
        while (actual != null) {
            if (actual.mision.getDescripcion().equals(descripcion)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }
}

/** Clase principal para probar el funcionamiento del DiarioAventurero*/
public class Main {
    public static void main(String[] args) {
        DiarioAventurero diario = new DiarioAventurero();

        // Registrar algunas misiones
        System.out.println("Registrando misiones...");
        diario.registrarMision("Derrotar al dragón");
        diario.registrarMision("Encontrar el tesoro perdido");
        diario.registrarMision("Salvar al príncipe");

        // Mostrar todas las misiones
        System.out.println("\nMisiones registradas:");
        diario.mostrarMisiones();

        // Buscar una misión específica
        String busqueda = "Encontrar el tesoro perdido";
        System.out.println("\nBuscando misión: " + busqueda);
        System.out.println("¿Se encuentra la misión? " + diario.buscarMision(busqueda));

        // Eliminar la última misión por fallida
        System.out.println("\nEliminando última misión por fallida...");
        diario.eliminarUltimaMision();

        // Mostrar misiones actualizadas
        System.out.println("\nMisiones después de eliminar la última:");
        diario.mostrarMisiones();
    }
}
