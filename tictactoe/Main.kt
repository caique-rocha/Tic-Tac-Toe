package tictactoe

fun main() {
    // write your code here
    var count = 1
    val input = CharArray(9) {' '}
    printGame(input)

    while (count <= 9){
        print("Enter the coordinates: ")
        count += getMove(readLine()!!.toString(), input, count)

        if (checkGame(input) != "Game not finished"){
            println(checkGame(input))
            break
        }
    }
}

fun checkGame(input: CharArray) : String{

    var x = 0
    var o = 0
    var countX = 0
    var countO = 0
    var space = 0

    for (e in input){
        when (e) {
            'X' -> {
                countX++
            }
            'O' -> {
                countO++
            }
            else -> {
                space++
            }
        }
    }

    // horizontal
    for (i in 0..8 step 3) {

        if (input[i] == 'X' && input[i + 1] == 'X' && input[i + 2] ==  'X') {
            x++
        }
        if (input[i] == 'O' && input[i + 1] == 'O' && input[i + 2] ==  'O') {
            o++
        }
    }

    // vertical
    for (i in 0..2) {

        if (input[i] == 'X' && input[i + 3] == 'X' && input[i + 6] ==  'X') {
            x++
        }
        if (input[i] == 'O' && input[i + 3] == 'O' && input[i + 6] ==  'O') {
            o++
        }
    }

    // diagonal positiva
    if (input[0] == 'X' && input[4] == 'X' && input[8] ==  'X') {
        x++
    }
    if (input[0] == 'O' && input[4] == 'O' && input[8] ==  'O') {
        o++
    }

    // diagonal negativa
    if (input[2] == 'X' && input[4] == 'X' && input[6] ==  'X') {
        x++
    }
    if (input[2] == 'O' && input[4] == 'O' && input[6] ==  'O') {
        o++
    }

    return ( when {

        x == 1 -> "X wins"
        o == 1 -> "O wins"
        o == 0 && x == 0  && space == 0-> "Draw"
        else -> "Game not finished"
    })

}

fun getMove(move: String, tab: CharArray, count: Int) : Int {

    val row = move.first()
    val col = move.last()
    val pos: Int

    if (row.isDigit() && col.isDigit()){

        pos = 3 * (row.toString().toInt() - 1) + (col.toString().toInt() - 1)
    } else {

        println("You should enter numbers!")
        return 0
    }

    if(row !in '1'..'3' || col !in '1'..'3') {

        println("Coordinates should be from 1 to 3!")
        return 0

    } else if (tab[pos] == 'X' || tab[pos]  == 'O') {

        println("This cell is occupied! Choose another one!")
        return 0

    } else {

        if (count % 2 == 0) {
            tab[pos] = 'O'
        } else {
            tab[pos] = 'X'
        }

        printGame(tab)
        return 1
    }
}

fun printGame(tab: CharArray) {

    println("---------")
    print("| ${tab[0]} ${tab[1]} ${tab[2]} |\n")
    print("| ${tab[3]} ${tab[4]} ${tab[5]} |\n")
    print("| ${tab[6]} ${tab[7]} ${tab[8]} |\n")
    println("---------")
}
