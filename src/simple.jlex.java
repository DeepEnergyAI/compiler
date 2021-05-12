import java_cup.runtime.*; // defines the Symbol class
// The generated scanner will return a Symbol for each token that it finds.
// A Symbol contains an Object field named value; that field will be of type
// TokenVal, defined below.
//
// A TokenVal object contains the line number on which the token occurs as
// well as the number of the character on that line that starts the token.
// Some tokens (e.g., literals) also include the value of the token.
class TokenVal {
 // fields
    int linenum;
    int charnum;
 // constructor
    TokenVal(int l, int c) {
        linenum = l;
	charnum = c;
    }
}
class IntLitTokenVal extends TokenVal {
 // new field: the value of the integer literal
    int intVal;
 // constructor
    IntLitTokenVal(int l, int c, int val) {
        super(l,c);
	    intVal = val;
    }
}
class IdLitTokenVal extends TokenVal {
    String idVal;
    IdLitTokenVal(int l, int c, String val) {
        super(l,c);
        idVal = val;
    }
}
class StringLitTokenVal extends TokenVal {
    String stringVal;
    StringLitTokenVal(int l, int c, String val) {
        super(l,c);
        stringVal = val;
    }
}
// The following class is used to keep track of the character number at which
// the current token starts on its line.
class CharNum {
  static int num=1;
}


class Yylex implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_END,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_END,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NOT_ACCEPT,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_END,
		/* 52 */ YY_END,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NOT_ACCEPT,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_END,
		/* 58 */ YY_NOT_ACCEPT,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_END,
		/* 62 */ YY_NOT_ACCEPT,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_END,
		/* 65 */ YY_NOT_ACCEPT,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NOT_ACCEPT,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NOT_ACCEPT,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NOT_ACCEPT,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NOT_ACCEPT,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NOT_ACCEPT,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NOT_ACCEPT,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NOT_ACCEPT,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NOT_ACCEPT,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NOT_ACCEPT,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NOT_ACCEPT,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NOT_ACCEPT,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NOT_ACCEPT,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NOT_ACCEPT,
		/* 92 */ YY_NOT_ACCEPT,
		/* 93 */ YY_NOT_ACCEPT,
		/* 94 */ YY_NOT_ACCEPT,
		/* 95 */ YY_NOT_ACCEPT,
		/* 96 */ YY_NOT_ACCEPT,
		/* 97 */ YY_NOT_ACCEPT,
		/* 98 */ YY_NOT_ACCEPT,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NOT_ACCEPT,
		/* 101 */ YY_END,
		/* 102 */ YY_END,
		/* 103 */ YY_NOT_ACCEPT,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NO_ANCHOR,
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NO_ANCHOR,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NO_ANCHOR,
		/* 139 */ YY_NO_ANCHOR,
		/* 140 */ YY_NO_ANCHOR,
		/* 141 */ YY_NO_ANCHOR,
		/* 142 */ YY_NO_ANCHOR,
		/* 143 */ YY_NO_ANCHOR,
		/* 144 */ YY_NO_ANCHOR,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NO_ANCHOR,
		/* 148 */ YY_NO_ANCHOR,
		/* 149 */ YY_NO_ANCHOR,
		/* 150 */ YY_NO_ANCHOR,
		/* 151 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"29:9,35,24,29,35,27,29:18,35,38,28,29:3,39,30,43,44,26,36,45,37,29,25,34:10" +
",29,47,48,46,49,29:2,32:18,1,32:7,29,11,29:2,33,29,17,16,18,19,9,20,6,23,4," +
"32:2,15,10,5,12,14,32,3,8,2,13,21,22,32,7,32,41,40,42,29:2,0,31")[0];

	private int yy_rmap[] = unpackFromString(1,152,
"0,1,2,1:2,3,1,4,5,1:2,6,1:5,7,1,8,9,10:2,11,1:7,10,1,10:3,1:2,10:9,12,13,14" +
",15,16,17,1,16,18,19,15,20,21,22,23,17,24,25,26,27,25,28,29,30,15,31,23,32," +
"33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57," +
"58,15,39,15,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79," +
"80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,10," +
"103,104,105")[0];

	private int yy_nxt[][] = unpackFromString(106,50,
"1,2,117,147,48,148:3,149,118,148,3,148:2,150,148,151,148,131,55,132,119,133" +
",148,4,5,6,7,49,3:2,1,148,3,8,7,9,10,11,56,60,12,13,14,15,16,17,18,19,20,-1" +
":51,148,134,148:4,135,148:3,-1,148:12,-1:8,148:3,-1:40,23,47,-1:50,7,-1:7,7" +
",-1:48,8,-1:61,25,-1:49,28,-1:49,29,-1:49,30,-1:4,148:10,-1,148:12,-1:8,148" +
":3,-1:16,23:23,-1,23:2,-1,23:3,-1,23:18,-1,47:25,58,47:4,-1,47:18,-1,148:4," +
"59,148:5,-1,148:8,21,148:3,-1:8,148:3,-1:16,54:10,100,54:12,-1,54:3,24,54:2" +
",-1,54:18,-1,71:23,37,71:2,101,71:3,37,71:18,-1,54:10,100,54:12,32,54:2,51," +
"24,54:2,32,54:18,-1,62:10,69,62:12,37,62:2,52,71,62:2,37,62:18,-1,148:10,-1" +
",22,148:11,-1:8,148:3,-1:54,26,-1:11,47:24,36,58,47:4,-1,47:18,-1,148,31,14" +
"8:8,-1,148:12,-1:8,148:3,-1:55,27,-1:10,73:10,77,73:12,32,73:2,61,50,73:2,3" +
"2,73:18,-1,148:8,33,148,-1,148:12,-1:8,148:3,-1:16,67:10,75,67:12,32,67:2,6" +
"4,24,67:2,32,67:18,-1,73,54,73:2,54,73:5,100,73:12,57,73:2,61,24,73,54,32,7" +
"3:18,-1,148:8,34,148,-1,148:12,-1:8,148:3,-1:16,148:10,-1,148:7,35,148:4,-1" +
":8,148:3,-1:16,62:10,69,62:12,101,62:2,52,71,62:2,37,62:18,-1,148:7,38,148:" +
"2,-1,148:12,-1:8,148:3,-1:16,148:8,39,148,-1,148:12,-1:8,148:3,-1:16,148:8," +
"40,148,-1,148:12,-1:8,148:3,-1:17,67,-1:2,67,-1:5,67,-1:16,67,-1,67,-1:20,1" +
"48:5,41,148:4,-1,148:12,-1:8,148:3,-1:16,62,73,62:2,73,62:5,79,62:12,101,62" +
":2,52,81,62,73,37,62:18,-1,148:4,42,148:5,-1,148:12,-1:8,148:3,-1:16,73:10," +
"77,73:12,57,73:2,61,50,73:2,32,73:18,-1,148:10,-1,148:6,43,148:5,-1:8,148:3" +
",-1:16,81:10,85,81:12,32,81:2,102,50,81:2,32,81:18,-1,148:10,-1,148:6,44,14" +
"8:5,-1:8,148:3,-1:27,87,-1:38,142:10,83,142:12,-1,83:2,-1,83:3,-1,142:3,83:" +
"15,-1,71,81,71:2,81,71:5,81,71:12,37,71:2,101,81,71,81,37,71:18,-1,148:4,45" +
",148:5,-1,148:12,-1:8,148:3,-1:28,89,-1:37,143:10,92,143:12,-1,92:2,-1,92:3" +
",-1,143:3,92:15,-1:2,91,-1:48,148:4,46,148:5,-1,148:12,-1:8,148:3,-1:16,92:" +
"23,-1,92:2,-1,92:3,-1,92:18,-1:14,93,-1:38,94,-1:50,95,-1:50,96,-1:46,97,-1" +
":62,98,-1:39,53,-1:45,148:10,-1,148,63,148:10,-1:8,148:3,-1:16,62,54,62:2,5" +
"4,62:5,65,62:12,103,62:3,67,62,54,-1,62:18,-1,148:7,66,148:2,-1,148:12,-1:8" +
",148:3,-1:16,148:3,68,148:6,-1,148:12,-1:8,148:3,-1:16,148:7,70,148:2,-1,14" +
"8:12,-1:8,148:3,-1:16,148:7,72,148:2,-1,148:12,-1:8,148:3,-1:16,148:10,-1,1" +
"48:3,74,148:8,-1:8,148:3,-1:16,148:4,76,148:5,-1,148:12,-1:8,148:3,-1:16,14" +
"8:2,78,148:7,-1,148:12,-1:8,148:3,-1:16,148:3,80,148:6,-1,148:12,-1:8,148:3" +
",-1:16,148:3,82,148:6,-1,148:12,-1:8,148:3,-1:16,148:9,84,-1,148:12,-1:8,14" +
"8:3,-1:16,148:10,-1,148:5,86,148:6,-1:8,148:3,-1:16,148,88,148:8,-1,148:12," +
"-1:8,148:3,-1:16,148:10,-1,148:3,90,148:8,-1:8,148:3,-1:16,148:2,99,148:7,-" +
"1,148:12,-1:8,148:3,-1:16,148:10,-1,148:3,104,148:8,-1:8,148:3,-1:16,148:10" +
",-1,105,148:11,-1:8,148:3,-1:16,148:10,-1,148:5,106,148:6,-1:8,148:3,-1:16," +
"148:10,-1,148:3,107,148:8,-1:8,148:3,-1:16,148:3,108,148:6,-1,148:12,-1:8,1" +
"48:3,-1:16,148:3,109,148:6,-1,148:12,-1:8,148:3,-1:16,148:10,-1,148,110,148" +
":10,-1:8,148:3,-1:16,148,111,148:8,-1,148:12,-1:8,148:3,-1:16,148:10,-1,148" +
":3,112,148:8,-1:8,148:3,-1:16,148:8,113,148,-1,148:12,-1:8,148:3,-1:16,148:" +
"8,114,148,-1,148:12,-1:8,148:3,-1:16,148:10,-1,148,115,148:10,-1:8,148:3,-1" +
":16,148,116,148:8,-1,148:12,-1:8,148:3,-1:16,148:10,-1,148:3,120,148:8,-1:8" +
",148:3,-1:16,148:10,-1,148:5,121,148:6,-1:8,148:3,-1:16,148:10,-1,148:11,12" +
"2,-1:8,148:3,-1:16,148:2,123,148:7,-1,148:12,-1:8,148:3,-1:16,148:7,140,148" +
":2,-1,148:12,-1:8,148:3,-1:16,148,124,148:8,-1,148:12,-1:8,148:3,-1:16,148:" +
"10,-1,148:5,125,148:6,-1:8,148:3,-1:16,148:10,-1,148:4,126,148:7,-1:8,148:3" +
",-1:16,148:10,-1,141,148:11,-1:8,148:3,-1:16,148,127,148:8,-1,148:12,-1:8,1" +
"48:3,-1:16,148:10,-1,148:3,128,148:8,-1:8,148:3,-1:16,148:10,-1,129,148:11," +
"-1:8,148:3,-1:16,148:10,-1,148:2,144,148:9,-1:8,148:3,-1:16,148:2,145,148:7" +
",-1,148:12,-1:8,148:3,-1:16,148:3,146,148:6,-1,148:12,-1:8,148:3,-1:16,148:" +
"4,130,148:5,-1,148:12,-1:8,148:3,-1:16,148:8,136,148,-1,148:12,-1:8,148:3,-" +
"1:16,148,137,148:8,-1,148:12,-1:8,148:3,-1:16,148:10,-1,148,138,148:10,-1:8" +
",148:3,-1:16,148:10,-1,139,148:11,-1:8,148:3,-1:15");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

return new Symbol(sym.EOF);
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -3:
						break;
					case 3:
						{Errors.fatal(yyline+1, CharNum.num,
			 "ignoring illegal character: " + yytext());
	    CharNum.num++;
	   }
					case -4:
						break;
					case 4:
						{
            CharNum.num = 1;
            }
					case -5:
						break;
					case 5:
						{Symbol S = new Symbol(sym.DIVIDE, new TokenVal(yyline+1, CharNum.num));
	    CharNum.num++;
	    return S;
       }
					case -6:
						break;
					case 6:
						{Symbol S = new Symbol(sym.TIMES, new TokenVal(yyline+1, CharNum.num));
	    CharNum.num++;
	    return S;
	   }
					case -7:
						break;
					case 7:
						{CharNum.num += yytext().length();}
					case -8:
						break;
					case 8:
						{// NOTE: the following computation of the integer value does NOT
	    //       check for overflow.  This must be changed.
            long val = (new Long(yytext())).longValue();
            if(val > Integer.MAX_VALUE) {
                Errors.warn(yyline+1, CharNum.num,
                            "integer literal too large; using max value");
                Symbol S = new Symbol(sym.INTLITERAL,
                                      new IntLitTokenVal(yyline+1, CharNum.num, Integer.MAX_VALUE));
                CharNum.num += yytext().length();
                return S;
            } else {
                Symbol S = new Symbol(sym.INTLITERAL,
                                      new IntLitTokenVal(yyline+1, CharNum.num, (int)val));
                CharNum.num += yytext().length();
                return S;
            }
	    }
					case -9:
						break;
					case 9:
						{Symbol S = new Symbol(sym.PLUS, new TokenVal(yyline+1, CharNum.num));
	    CharNum.num++;
	    return S;
	   }
					case -10:
						break;
					case 10:
						{Symbol S = new Symbol(sym.MINUS, new TokenVal(yyline+1, CharNum.num));
	    CharNum.num++;
	    return S;
	   }
					case -11:
						break;
					case 11:
						{Symbol S = new Symbol(sym.NOT, new TokenVal(yyline+1, CharNum.num));
	    CharNum.num++;
	    return S;
	   }
					case -12:
						break;
					case 12:
						{Symbol S = new Symbol(sym.LCURLY, new TokenVal(yyline+1, CharNum.num));
	    CharNum.num++;
	    return S;
	   }
					case -13:
						break;
					case 13:
						{Symbol S = new Symbol(sym.RCURLY, new TokenVal(yyline+1, CharNum.num));
	    CharNum.num++;
	    return S;
	   }
					case -14:
						break;
					case 14:
						{Symbol S = new Symbol(sym.LPAREN, new TokenVal(yyline+1, CharNum.num));
	    CharNum.num++;
	    return S;
	   }
					case -15:
						break;
					case 15:
						{Symbol S = new Symbol(sym.RPAREN, new TokenVal(yyline+1, CharNum.num));
	    CharNum.num++;
	    return S;
	   }
					case -16:
						break;
					case 16:
						{Symbol S = new Symbol(sym.COMMA, new TokenVal(yyline+1, CharNum.num));
	    CharNum.num++;
	    return S;
	   }
					case -17:
						break;
					case 17:
						{Symbol S = new Symbol(sym.ASSIGN, new TokenVal(yyline+1, CharNum.num));
	    CharNum.num++;
	    return S;
	   }
					case -18:
						break;
					case 18:
						{Symbol S = new Symbol(sym.SEMICOLON, new TokenVal(yyline+1, CharNum.num));
	    CharNum.num++;
	    return S;
	   }
					case -19:
						break;
					case 19:
						{Symbol S = new Symbol(sym.LESS, new TokenVal(yyline+1, CharNum.num));
	    CharNum.num++;
	    return S;
	   }
					case -20:
						break;
					case 20:
						{Symbol S = new Symbol(sym.GREATER, new TokenVal(yyline+1, CharNum.num));
	    CharNum.num++;
	    return S;
	   }
					case -21:
						break;
					case 21:
						{
            Symbol S = new Symbol(sym.IF, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
        }
					case -22:
						break;
					case 22:
						{
            Symbol S = new Symbol(sym.DO, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
        }
					case -23:
						break;
					case 23:
						{CharNum.num += yytext().length();}
					case -24:
						break;
					case 24:
						{
        String val = yytext();
        val = val.replace("\\n", "\n");
        val = val.replace("\\t", "\t");
        Symbol S = new Symbol(sym.STRINGLITERAL,
                       new StringLitTokenVal(yyline+1, CharNum.num, val)
                );
        CharNum.num += yytext().length();
        return S;
        }
					case -25:
						break;
					case 25:
						{Symbol S = new Symbol(sym.NOTEQUALS, new TokenVal(yyline+1, CharNum.num));
	    CharNum.num++;
	    return S;
	   }
					case -26:
						break;
					case 26:
						{Symbol S = new Symbol(sym.AND, new TokenVal(yyline+1, CharNum.num));
	    CharNum.num += yytext().length();
	    return S;
	   }
					case -27:
						break;
					case 27:
						{Symbol S = new Symbol(sym.OR, new TokenVal(yyline+1, CharNum.num));
	    CharNum.num += yytext().length();
	    return S;
	   }
					case -28:
						break;
					case 28:
						{Symbol S = new Symbol(sym.EQUALS, new TokenVal(yyline+1, CharNum.num));
	    CharNum.num += yytext().length();
	    return S;
	   }
					case -29:
						break;
					case 29:
						{Symbol S = new Symbol(sym.LESSEQ, new TokenVal(yyline+1, CharNum.num));
	    CharNum.num += yytext().length();
	    return S;
	   }
					case -30:
						break;
					case 30:
						{Symbol S = new Symbol(sym.GREATEREQ, new TokenVal(yyline+1, CharNum.num));
	    CharNum.num += yytext().length();
	    return S;
	   }
					case -31:
						break;
					case 31:
						{
            Symbol S = new Symbol(sym.INT, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
        }
					case -32:
						break;
					case 32:
						{
        Errors.fatal(yyline+1, CharNum.num,
                     "Unterminated string literals");
        CharNum.num += yytext().length();
        }
					case -33:
						break;
					case 33:
						{
            Symbol S = new Symbol(sym.TRUE, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
        }
					case -34:
						break;
					case 34:
						{
            Symbol S = new Symbol(sym.ELSE, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
        }
					case -35:
						break;
					case 35:
						{
            Symbol S = new Symbol(sym.VOID, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
        }
					case -36:
						break;
					case 36:
						{CharNum.num += yytext().length();}
					case -37:
						break;
					case 37:
						{
        Errors.fatal(yyline+1, CharNum.num,
        			 "Ignoring string literal with bad escaped character");
        CharNum.num += yytext().length();
        }
					case -38:
						break;
					case 38:
						{
            Symbol S = new Symbol(sym.CLASS, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
        }
					case -39:
						break;
					case 39:
						{
            Symbol S = new Symbol(sym.FALSE, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
        }
					case -40:
						break;
					case 40:
						{
            Symbol S = new Symbol(sym.WHILE, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
        }
					case -41:
						break;
					case 41:
						{
            Symbol S = new Symbol(sym.STRING, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
        }
					case -42:
						break;
					case 42:
						{
            Symbol S = new Symbol(sym.RETURN, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
        }
					case -43:
						break;
					case 43:
						{
            Symbol S = new Symbol(sym.STATIC, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
        }
					case -44:
						break;
					case 44:
						{
            Symbol S = new Symbol(sym.PUBLIC, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
        }
					case -45:
						break;
					case 45:
						{
            Symbol S = new Symbol(sym.BOOLEAN, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
        }
					case -46:
						break;
					case 46:
						{
            Symbol S = new Symbol(sym.PRINT, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
        }
					case -47:
						break;
					case 48:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -48:
						break;
					case 49:
						{Errors.fatal(yyline+1, CharNum.num,
			 "ignoring illegal character: " + yytext());
	    CharNum.num++;
	   }
					case -49:
						break;
					case 50:
						{
        String val = yytext();
        val = val.replace("\\n", "\n");
        val = val.replace("\\t", "\t");
        Symbol S = new Symbol(sym.STRINGLITERAL,
                       new StringLitTokenVal(yyline+1, CharNum.num, val)
                );
        CharNum.num += yytext().length();
        return S;
        }
					case -50:
						break;
					case 51:
						{
        Errors.fatal(yyline+1, CharNum.num,
                     "Unterminated string literals");
        CharNum.num += yytext().length();
        }
					case -51:
						break;
					case 52:
						{
        Errors.fatal(yyline+1, CharNum.num,
        			 "Ignoring string literal with bad escaped character");
        CharNum.num += yytext().length();
        }
					case -52:
						break;
					case 53:
						{
            Symbol S = new Symbol(sym.PRINT, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
        }
					case -53:
						break;
					case 55:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -54:
						break;
					case 56:
						{Errors.fatal(yyline+1, CharNum.num,
			 "ignoring illegal character: " + yytext());
	    CharNum.num++;
	   }
					case -55:
						break;
					case 57:
						{
        Errors.fatal(yyline+1, CharNum.num,
                     "Unterminated string literals");
        CharNum.num += yytext().length();
        }
					case -56:
						break;
					case 59:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -57:
						break;
					case 60:
						{Errors.fatal(yyline+1, CharNum.num,
			 "ignoring illegal character: " + yytext());
	    CharNum.num++;
	   }
					case -58:
						break;
					case 61:
						{
        Errors.fatal(yyline+1, CharNum.num,
                     "Unterminated string literals");
        CharNum.num += yytext().length();
        }
					case -59:
						break;
					case 63:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -60:
						break;
					case 64:
						{
        Errors.fatal(yyline+1, CharNum.num,
                     "Unterminated string literals");
        CharNum.num += yytext().length();
        }
					case -61:
						break;
					case 66:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -62:
						break;
					case 68:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -63:
						break;
					case 70:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -64:
						break;
					case 72:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -65:
						break;
					case 74:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -66:
						break;
					case 76:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -67:
						break;
					case 78:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -68:
						break;
					case 80:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -69:
						break;
					case 82:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -70:
						break;
					case 84:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -71:
						break;
					case 86:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -72:
						break;
					case 88:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -73:
						break;
					case 90:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -74:
						break;
					case 99:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -75:
						break;
					case 101:
						{
        Errors.fatal(yyline+1, CharNum.num,
        			 "Ignoring string literal with bad escaped character");
        CharNum.num += yytext().length();
        }
					case -76:
						break;
					case 102:
						{
        Errors.fatal(yyline+1, CharNum.num,
                     "Unterminated string literals");
        CharNum.num += yytext().length();
        }
					case -77:
						break;
					case 104:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -78:
						break;
					case 105:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -79:
						break;
					case 106:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -80:
						break;
					case 107:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -81:
						break;
					case 108:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -82:
						break;
					case 109:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -83:
						break;
					case 110:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -84:
						break;
					case 111:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -85:
						break;
					case 112:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -86:
						break;
					case 113:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -87:
						break;
					case 114:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -88:
						break;
					case 115:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -89:
						break;
					case 116:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -90:
						break;
					case 117:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -91:
						break;
					case 118:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -92:
						break;
					case 119:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -93:
						break;
					case 120:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -94:
						break;
					case 121:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -95:
						break;
					case 122:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -96:
						break;
					case 123:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -97:
						break;
					case 124:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -98:
						break;
					case 125:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -99:
						break;
					case 126:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -100:
						break;
					case 127:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -101:
						break;
					case 128:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -102:
						break;
					case 129:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -103:
						break;
					case 130:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -104:
						break;
					case 131:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -105:
						break;
					case 132:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -106:
						break;
					case 133:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -107:
						break;
					case 134:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -108:
						break;
					case 135:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -109:
						break;
					case 136:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -110:
						break;
					case 137:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -111:
						break;
					case 138:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -112:
						break;
					case 139:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -113:
						break;
					case 140:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -114:
						break;
					case 141:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -115:
						break;
					case 142:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -116:
						break;
					case 143:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -117:
						break;
					case 144:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -118:
						break;
					case 145:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -119:
						break;
					case 146:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -120:
						break;
					case 147:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -121:
						break;
					case 148:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -122:
						break;
					case 149:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -123:
						break;
					case 150:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -124:
						break;
					case 151:
						{
        String val = yytext();
        Symbol S = new Symbol(sym.ID,
                       new IdLitTokenVal(yyline+1, CharNum.num, val)
                 );
        CharNum.num += yytext().length();
        return S;
        }
					case -125:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
