public class ListaHeroe {
    NodoHeroe cabeza;

    public ListaHeroe() {
        this.cabeza = null;
    }

    public boolean agregarHeroe(SpiderverseHero nuevoHeroe) {
        if (buscarPorCodigo(nuevoHeroe.getCodigo()) != null) {
            return false; // Código ya existe
        }
        NodoHeroe nuevoNodo = new NodoHeroe(nuevoHeroe);
        nuevoNodo.siguiente = cabeza;
        cabeza = nuevoNodo;
        return true;
    }

    public SpiderverseHero buscarPorCodigo(int codigo) {
        NodoHeroe actual = cabeza;
        while (actual != null) {
            if (actual.heroe.getCodigo() == codigo) {
                return actual.heroe;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public boolean eliminarPorCodigo(int codigo) {
        if (cabeza == null) return false;
        if (cabeza.heroe.getCodigo() == codigo) {
            cabeza = cabeza.siguiente;
            return true;
        }
        NodoHeroe actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.heroe.getCodigo() == codigo) {
                actual.siguiente = actual.siguiente.siguiente;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public int contar() {
        int contador = 0;
        NodoHeroe actual = cabeza;
        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }
        return contador;
    }

    public SpiderverseHero[] obtenerTodos() {
        int total = contar();
        SpiderverseHero[] lista = new SpiderverseHero[total];
        NodoHeroe actual = cabeza;
        int i = 0;
        while (actual != null) {
            lista[i++] = actual.heroe;
            actual = actual.siguiente;
        }
        return lista;
    }
}
