//~This file was automatically generated via CupAndLexEasyGenerator 1.0.0.1~
//~Author:Jordan Micah Bennett~

/* Author : Jordan Micah Bennett */
/* Hierarchy : exterior -> interior {statement -> empty string} */
// user customisations
package data.packages.CONSCIENCIA.SYNTAX;
import java_cup.runtime.*;
// Jlex directives


public class CONSCIENCIALexer implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

    public int getChar() {
	return yychar + 1;
    }
    public int getLine() {
	return yyline + 1;
    }
    public String getText() {
	return yytext();
    }
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public CONSCIENCIALexer (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public CONSCIENCIALexer (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private CONSCIENCIALexer () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYSTRING = 1;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0,
		239
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
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
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
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NOT_ACCEPT,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NOT_ACCEPT,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NOT_ACCEPT,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NOT_ACCEPT,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NOT_ACCEPT,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NOT_ACCEPT,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NOT_ACCEPT,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NOT_ACCEPT,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NOT_ACCEPT,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NOT_ACCEPT,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NOT_ACCEPT,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NOT_ACCEPT,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NOT_ACCEPT,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NOT_ACCEPT,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NOT_ACCEPT,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NOT_ACCEPT,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NOT_ACCEPT,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NOT_ACCEPT,
		/* 133 */ YY_NO_ANCHOR,
		/* 134 */ YY_NOT_ACCEPT,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NOT_ACCEPT,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NOT_ACCEPT,
		/* 139 */ YY_NO_ANCHOR,
		/* 140 */ YY_NOT_ACCEPT,
		/* 141 */ YY_NO_ANCHOR,
		/* 142 */ YY_NOT_ACCEPT,
		/* 143 */ YY_NO_ANCHOR,
		/* 144 */ YY_NOT_ACCEPT,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NOT_ACCEPT,
		/* 147 */ YY_NO_ANCHOR,
		/* 148 */ YY_NOT_ACCEPT,
		/* 149 */ YY_NO_ANCHOR,
		/* 150 */ YY_NOT_ACCEPT,
		/* 151 */ YY_NO_ANCHOR,
		/* 152 */ YY_NOT_ACCEPT,
		/* 153 */ YY_NO_ANCHOR,
		/* 154 */ YY_NOT_ACCEPT,
		/* 155 */ YY_NO_ANCHOR,
		/* 156 */ YY_NOT_ACCEPT,
		/* 157 */ YY_NO_ANCHOR,
		/* 158 */ YY_NOT_ACCEPT,
		/* 159 */ YY_NO_ANCHOR,
		/* 160 */ YY_NOT_ACCEPT,
		/* 161 */ YY_NOT_ACCEPT,
		/* 162 */ YY_NOT_ACCEPT,
		/* 163 */ YY_NOT_ACCEPT,
		/* 164 */ YY_NOT_ACCEPT,
		/* 165 */ YY_NOT_ACCEPT,
		/* 166 */ YY_NOT_ACCEPT,
		/* 167 */ YY_NOT_ACCEPT,
		/* 168 */ YY_NOT_ACCEPT,
		/* 169 */ YY_NOT_ACCEPT,
		/* 170 */ YY_NOT_ACCEPT,
		/* 171 */ YY_NOT_ACCEPT,
		/* 172 */ YY_NOT_ACCEPT,
		/* 173 */ YY_NOT_ACCEPT,
		/* 174 */ YY_NOT_ACCEPT,
		/* 175 */ YY_NOT_ACCEPT,
		/* 176 */ YY_NOT_ACCEPT,
		/* 177 */ YY_NOT_ACCEPT,
		/* 178 */ YY_NOT_ACCEPT,
		/* 179 */ YY_NOT_ACCEPT,
		/* 180 */ YY_NOT_ACCEPT,
		/* 181 */ YY_NOT_ACCEPT,
		/* 182 */ YY_NOT_ACCEPT,
		/* 183 */ YY_NOT_ACCEPT,
		/* 184 */ YY_NOT_ACCEPT,
		/* 185 */ YY_NOT_ACCEPT,
		/* 186 */ YY_NOT_ACCEPT,
		/* 187 */ YY_NOT_ACCEPT,
		/* 188 */ YY_NOT_ACCEPT,
		/* 189 */ YY_NOT_ACCEPT,
		/* 190 */ YY_NOT_ACCEPT,
		/* 191 */ YY_NOT_ACCEPT,
		/* 192 */ YY_NOT_ACCEPT,
		/* 193 */ YY_NOT_ACCEPT,
		/* 194 */ YY_NOT_ACCEPT,
		/* 195 */ YY_NOT_ACCEPT,
		/* 196 */ YY_NOT_ACCEPT,
		/* 197 */ YY_NOT_ACCEPT,
		/* 198 */ YY_NOT_ACCEPT,
		/* 199 */ YY_NOT_ACCEPT,
		/* 200 */ YY_NOT_ACCEPT,
		/* 201 */ YY_NOT_ACCEPT,
		/* 202 */ YY_NOT_ACCEPT,
		/* 203 */ YY_NOT_ACCEPT,
		/* 204 */ YY_NOT_ACCEPT,
		/* 205 */ YY_NOT_ACCEPT,
		/* 206 */ YY_NOT_ACCEPT,
		/* 207 */ YY_NOT_ACCEPT,
		/* 208 */ YY_NOT_ACCEPT,
		/* 209 */ YY_NOT_ACCEPT,
		/* 210 */ YY_NOT_ACCEPT,
		/* 211 */ YY_NOT_ACCEPT,
		/* 212 */ YY_NOT_ACCEPT,
		/* 213 */ YY_NOT_ACCEPT,
		/* 214 */ YY_NOT_ACCEPT,
		/* 215 */ YY_NOT_ACCEPT,
		/* 216 */ YY_NOT_ACCEPT,
		/* 217 */ YY_NOT_ACCEPT,
		/* 218 */ YY_NOT_ACCEPT,
		/* 219 */ YY_NOT_ACCEPT,
		/* 220 */ YY_NOT_ACCEPT,
		/* 221 */ YY_NOT_ACCEPT,
		/* 222 */ YY_NOT_ACCEPT,
		/* 223 */ YY_NOT_ACCEPT,
		/* 224 */ YY_NOT_ACCEPT,
		/* 225 */ YY_NOT_ACCEPT,
		/* 226 */ YY_NOT_ACCEPT,
		/* 227 */ YY_NOT_ACCEPT,
		/* 228 */ YY_NOT_ACCEPT,
		/* 229 */ YY_NOT_ACCEPT,
		/* 230 */ YY_NOT_ACCEPT,
		/* 231 */ YY_NOT_ACCEPT,
		/* 232 */ YY_NOT_ACCEPT,
		/* 233 */ YY_NOT_ACCEPT,
		/* 234 */ YY_NOT_ACCEPT,
		/* 235 */ YY_NOT_ACCEPT,
		/* 236 */ YY_NOT_ACCEPT,
		/* 237 */ YY_NOT_ACCEPT,
		/* 238 */ YY_NOT_ACCEPT,
		/* 239 */ YY_NOT_ACCEPT,
		/* 240 */ YY_NO_ANCHOR,
		/* 241 */ YY_NO_ANCHOR,
		/* 242 */ YY_NOT_ACCEPT,
		/* 243 */ YY_NOT_ACCEPT,
		/* 244 */ YY_NOT_ACCEPT,
		/* 245 */ YY_NOT_ACCEPT,
		/* 246 */ YY_NOT_ACCEPT,
		/* 247 */ YY_NOT_ACCEPT,
		/* 248 */ YY_NOT_ACCEPT,
		/* 249 */ YY_NOT_ACCEPT,
		/* 250 */ YY_NOT_ACCEPT,
		/* 251 */ YY_NOT_ACCEPT,
		/* 252 */ YY_NOT_ACCEPT,
		/* 253 */ YY_NOT_ACCEPT,
		/* 254 */ YY_NOT_ACCEPT,
		/* 255 */ YY_NOT_ACCEPT,
		/* 256 */ YY_NOT_ACCEPT,
		/* 257 */ YY_NO_ANCHOR,
		/* 258 */ YY_NO_ANCHOR,
		/* 259 */ YY_NOT_ACCEPT,
		/* 260 */ YY_NOT_ACCEPT,
		/* 261 */ YY_NOT_ACCEPT,
		/* 262 */ YY_NOT_ACCEPT,
		/* 263 */ YY_NOT_ACCEPT,
		/* 264 */ YY_NO_ANCHOR,
		/* 265 */ YY_NO_ANCHOR,
		/* 266 */ YY_NOT_ACCEPT,
		/* 267 */ YY_NOT_ACCEPT,
		/* 268 */ YY_NO_ANCHOR,
		/* 269 */ YY_NO_ANCHOR,
		/* 270 */ YY_NO_ANCHOR,
		/* 271 */ YY_NO_ANCHOR,
		/* 272 */ YY_NO_ANCHOR,
		/* 273 */ YY_NO_ANCHOR,
		/* 274 */ YY_NO_ANCHOR,
		/* 275 */ YY_NO_ANCHOR,
		/* 276 */ YY_NO_ANCHOR,
		/* 277 */ YY_NO_ANCHOR,
		/* 278 */ YY_NO_ANCHOR,
		/* 279 */ YY_NO_ANCHOR,
		/* 280 */ YY_NO_ANCHOR,
		/* 281 */ YY_NO_ANCHOR,
		/* 282 */ YY_NO_ANCHOR,
		/* 283 */ YY_NO_ANCHOR,
		/* 284 */ YY_NO_ANCHOR,
		/* 285 */ YY_NO_ANCHOR,
		/* 286 */ YY_NO_ANCHOR,
		/* 287 */ YY_NO_ANCHOR,
		/* 288 */ YY_NO_ANCHOR,
		/* 289 */ YY_NO_ANCHOR,
		/* 290 */ YY_NO_ANCHOR,
		/* 291 */ YY_NO_ANCHOR,
		/* 292 */ YY_NO_ANCHOR,
		/* 293 */ YY_NO_ANCHOR,
		/* 294 */ YY_NO_ANCHOR,
		/* 295 */ YY_NO_ANCHOR,
		/* 296 */ YY_NO_ANCHOR,
		/* 297 */ YY_NO_ANCHOR,
		/* 298 */ YY_NO_ANCHOR,
		/* 299 */ YY_NO_ANCHOR,
		/* 300 */ YY_NO_ANCHOR,
		/* 301 */ YY_NO_ANCHOR,
		/* 302 */ YY_NO_ANCHOR,
		/* 303 */ YY_NO_ANCHOR,
		/* 304 */ YY_NO_ANCHOR,
		/* 305 */ YY_NO_ANCHOR,
		/* 306 */ YY_NO_ANCHOR,
		/* 307 */ YY_NOT_ACCEPT,
		/* 308 */ YY_NOT_ACCEPT,
		/* 309 */ YY_NOT_ACCEPT,
		/* 310 */ YY_NO_ANCHOR,
		/* 311 */ YY_NOT_ACCEPT,
		/* 312 */ YY_NO_ANCHOR,
		/* 313 */ YY_NO_ANCHOR,
		/* 314 */ YY_NO_ANCHOR,
		/* 315 */ YY_NO_ANCHOR,
		/* 316 */ YY_NO_ANCHOR,
		/* 317 */ YY_NO_ANCHOR,
		/* 318 */ YY_NO_ANCHOR,
		/* 319 */ YY_NO_ANCHOR,
		/* 320 */ YY_NO_ANCHOR,
		/* 321 */ YY_NO_ANCHOR,
		/* 322 */ YY_NO_ANCHOR,
		/* 323 */ YY_NO_ANCHOR,
		/* 324 */ YY_NO_ANCHOR,
		/* 325 */ YY_NO_ANCHOR,
		/* 326 */ YY_NO_ANCHOR,
		/* 327 */ YY_NO_ANCHOR,
		/* 328 */ YY_NO_ANCHOR,
		/* 329 */ YY_NO_ANCHOR,
		/* 330 */ YY_NO_ANCHOR,
		/* 331 */ YY_NO_ANCHOR,
		/* 332 */ YY_NO_ANCHOR,
		/* 333 */ YY_NO_ANCHOR,
		/* 334 */ YY_NO_ANCHOR,
		/* 335 */ YY_NO_ANCHOR,
		/* 336 */ YY_NO_ANCHOR,
		/* 337 */ YY_NO_ANCHOR,
		/* 338 */ YY_NO_ANCHOR,
		/* 339 */ YY_NO_ANCHOR,
		/* 340 */ YY_NO_ANCHOR,
		/* 341 */ YY_NO_ANCHOR,
		/* 342 */ YY_NO_ANCHOR,
		/* 343 */ YY_NO_ANCHOR,
		/* 344 */ YY_NO_ANCHOR,
		/* 345 */ YY_NO_ANCHOR,
		/* 346 */ YY_NO_ANCHOR,
		/* 347 */ YY_NO_ANCHOR,
		/* 348 */ YY_NO_ANCHOR,
		/* 349 */ YY_NO_ANCHOR,
		/* 350 */ YY_NO_ANCHOR,
		/* 351 */ YY_NO_ANCHOR,
		/* 352 */ YY_NO_ANCHOR,
		/* 353 */ YY_NO_ANCHOR,
		/* 354 */ YY_NO_ANCHOR,
		/* 355 */ YY_NO_ANCHOR,
		/* 356 */ YY_NO_ANCHOR,
		/* 357 */ YY_NO_ANCHOR,
		/* 358 */ YY_NO_ANCHOR,
		/* 359 */ YY_NO_ANCHOR,
		/* 360 */ YY_NO_ANCHOR,
		/* 361 */ YY_NO_ANCHOR,
		/* 362 */ YY_NO_ANCHOR,
		/* 363 */ YY_NO_ANCHOR,
		/* 364 */ YY_NO_ANCHOR,
		/* 365 */ YY_NO_ANCHOR,
		/* 366 */ YY_NO_ANCHOR,
		/* 367 */ YY_NO_ANCHOR,
		/* 368 */ YY_NO_ANCHOR,
		/* 369 */ YY_NO_ANCHOR,
		/* 370 */ YY_NO_ANCHOR,
		/* 371 */ YY_NO_ANCHOR,
		/* 372 */ YY_NOT_ACCEPT,
		/* 373 */ YY_NOT_ACCEPT,
		/* 374 */ YY_NO_ANCHOR,
		/* 375 */ YY_NO_ANCHOR,
		/* 376 */ YY_NO_ANCHOR,
		/* 377 */ YY_NO_ANCHOR,
		/* 378 */ YY_NO_ANCHOR,
		/* 379 */ YY_NO_ANCHOR,
		/* 380 */ YY_NO_ANCHOR,
		/* 381 */ YY_NO_ANCHOR,
		/* 382 */ YY_NO_ANCHOR,
		/* 383 */ YY_NOT_ACCEPT,
		/* 384 */ YY_NO_ANCHOR,
		/* 385 */ YY_NO_ANCHOR,
		/* 386 */ YY_NO_ANCHOR,
		/* 387 */ YY_NO_ANCHOR,
		/* 388 */ YY_NO_ANCHOR,
		/* 389 */ YY_NO_ANCHOR,
		/* 390 */ YY_NO_ANCHOR,
		/* 391 */ YY_NO_ANCHOR,
		/* 392 */ YY_NO_ANCHOR,
		/* 393 */ YY_NO_ANCHOR,
		/* 394 */ YY_NO_ANCHOR,
		/* 395 */ YY_NO_ANCHOR,
		/* 396 */ YY_NO_ANCHOR,
		/* 397 */ YY_NO_ANCHOR,
		/* 398 */ YY_NOT_ACCEPT,
		/* 399 */ YY_NOT_ACCEPT,
		/* 400 */ YY_NO_ANCHOR,
		/* 401 */ YY_NO_ANCHOR,
		/* 402 */ YY_NOT_ACCEPT,
		/* 403 */ YY_NOT_ACCEPT,
		/* 404 */ YY_NOT_ACCEPT,
		/* 405 */ YY_NOT_ACCEPT,
		/* 406 */ YY_NOT_ACCEPT,
		/* 407 */ YY_NOT_ACCEPT,
		/* 408 */ YY_NO_ANCHOR,
		/* 409 */ YY_NO_ANCHOR,
		/* 410 */ YY_NO_ANCHOR,
		/* 411 */ YY_NOT_ACCEPT,
		/* 412 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"67:8,2:2,1,67,2,1,67:18,19,51,68,63,67,9,45,6,60,4,5,7,10,8,12,3,64:10,42,1" +
"1,50,48,49,53,52,66:3,24,34,37,69:2,43,69:3,32,69:2,28,31,40,13,69:2,59,69:" +
"4,54,65,55,67:3,16,58,30,29,18,26,22,39,25,69:2,33,15,21,20,36,57,27,23,17," +
"14,41,38,35,44,56,61,46,62,47,67,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,413,
"0,1:3,2,1,3,1:5,4,5,6,1:4,7,8,1,9,1:4,10,11,12,1,13,14,15:3,1:12,15:8,1,15," +
"16,15,17,15:4,18,15:2,1:2,15:5,1,15:2,1,15,1:14,19,20,1,21,22,23,24,25,26,2" +
"7,28,29,30,31,21,32,33,34,35,36,37,38,39,40,12,41,42,43,44,45,46,47,48,49,5" +
"0,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,7" +
"5,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,1" +
"00,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118," +
"119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137" +
",138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,15" +
"6,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,1" +
"75,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193," +
"194,195,196,197,198,199,200,201,202,203,204,205,206,207,208,209,210,211,212" +
",213,214,215,216,217,218,219,220,221,222,223,224,225,226,227,228,229,230,23" +
"1,232,233,234,235,236,237,238,239,240,241,242,243,244,245,246,247,248,249,2" +
"50,251,252,253,254,255,256,257,258,259,260,261,262,263,264,265,266,267,268," +
"269,270,271,272,273,274,275,276,277,278,279,280,281,282,283,284,285,286,287" +
",288,289,290,291,292,15,293,294,295,296,297,298,299,300,301,302,303,304,305" +
",306,307,308,309,310,311,312,313,314,315,316,317,318,319,320,321,322,323,32" +
"4,325,326,327,328,329,330,331,332,333")[0];

	private int yy_nxt[][] = unpackFromString(334,70,
"1,2,3,4,5,6,94,7,8,9,10,11,12,13,371,306,14,310,312,3,95,240,371,313,382,99" +
",314,315,387,371,257,390,392,264,393,371,316,394,371:2,395,371,98,102,371,1" +
"5,16,17,18,19,20,101,21,-1,22,23,371:3,396,24,25,26,104,27,-1,371,-1,106,37" +
"1,-1:73,28,-1,29,-1:67,30,-1:78,112,-1:51,31,-1:18,371,397,371:2,317,371,-1" +
",371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,-1,371,32" +
",371,33,371:18,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:48,38,-1:69" +
",39,-1:63,41,-1:39,31,-1:51,27,-1:7,28:68,-1,118:2,-1:2,120,118:54,-1,118:9" +
",-1:64,31,-1:18,371:6,-1,371:9,48,371:12,-1,371:2,-1:11,371:4,-1:4,318,-1,3" +
"71,-1:2,371,-1:13,371:6,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2" +
",371,-1:13,371:5,149,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,37" +
"1,-1:13,371:6,-1,371:5,296,371:16,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2" +
",371,-1:13,371:6,-1,371:22,-1,371:2,-1:8,68,-1:2,371:4,-1:4,318,-1,371,-1:2" +
",371,-1,108:5,-1,108:58,110,108:4,-1:13,371:6,-1,371:7,34,371:8,319,371:5,-" +
"1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:6,46,-1:111,36,-1:6,37,-1:2" +
"7,371:6,-1,371,278,371:4,35,371:15,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:" +
"2,371,-1:10,130,-1:5,130,-1,130,-1:5,130,-1,130,-1:2,130:2,-1:3,130,-1:2,13" +
"0,-1:20,130,-1:5,130,-1,130,-1:51,40,-1:34,371:6,114,371:22,-1,371:2,-1:11," +
"371:4,-1:4,318,-1,371,-1:2,371,-1:2,46:68,-1:17,42,43,-1:7,44,-1:38,116,-1:" +
"17,371:4,49,371,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1," +
"106:67,45,106,-1:13,371:6,-1,371:3,129,371:3,50,371:5,131,371:8,-1,371:2,-1" +
":11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,-1,371:7,51,371:14,-1,371:2," +
"-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:2,108:4,97,108:63,-1:13,371:4,52,37" +
"1,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:12,47,-1:70,37" +
"1:5,53,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:16,122,-1" +
":21,124,-1:44,371:6,-1,371,54,371:20,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-" +
"1:2,371,-1,46:5,-1,46:3,100,46:5,100,46,100,46:5,100,46,100,46:2,100:2,46:3" +
",100,46:2,100,46:20,100,46:5,100,103,100,46:3,-1:13,371:5,55,-1,371:22,-1,3" +
"71:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,-1,371:22,-1,371:2,-1" +
":8,56,-1:2,371:4,-1:4,318,-1,371,-1:2,371,-1:3,96,-1:79,371:5,57,-1,371:22," +
"-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:15,126,-1:67,371:6,-1,371:" +
"19,58,371:2,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:18,128,-1:64,3" +
"71:4,59,371,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:19,1" +
"34,-1:63,371:6,-1,371:9,60,371:12,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2" +
",371,-1:25,136,-1:57,371:5,61,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,37" +
"1,-1:2,371,-1:10,138,-1:5,138,-1,138,-1:5,138,-1,138,-1:2,138:2,-1:3,138,-1" +
":2,138,-1:20,138,-1:5,138,-1,138,-1:16,371:6,-1,371:13,62,371:8,-1,371:2,-1" +
":11,371:4,-1:4,318,-1,371,-1:2,371,-1:23,142,-1:6,144,-1:7,146,-1:44,371:6," +
"-1,371:22,-1,371,63,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:16,75,-1:66,371" +
":4,64,371,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:22,148" +
",-1:60,371:6,-1,371:7,65,371:14,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,3" +
"71,-1:10,46,-1:5,46,-1,46,-1:5,46,-1,46,-1:2,46:2,-1:3,46,-1:2,46,-1:20,46," +
"-1:5,46,-1,46,-1:16,371:6,-1,371:10,66,371:11,-1,371:2,-1:11,371:4,-1:4,318" +
",-1,371,-1:2,371,-1:16,150,-1:66,371:5,67,-1,371:22,-1,371:2,-1:11,371:4,-1" +
":4,318,-1,371,-1:2,371,-1:25,242,-1:57,371:6,-1,371:22,-1,371:2,-1:8,69,-1:" +
"2,371:4,-1:4,318,-1,371,-1:2,371,-1:20,259,-1:62,371:6,-1,371:7,70,371:14,-" +
"1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:18,152,-1:64,371:5,71,-1,37" +
"1:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:39,78,-1:43,371:6,-1," +
"371:3,72,371:18,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:15,158,-1:" +
"67,371:5,73,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:25,1" +
"62,-1:57,371:6,-1,371:3,74,371:18,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2" +
",371,-1:29,163,-1:53,371:6,-1,371:22,-1,371,76,-1:11,371:4,-1:4,318,-1,371," +
"-1:2,371,-1:16,164,-1:66,371:4,77,371,-1,371:22,-1,371:2,-1:11,371:4,-1:4,3" +
"18,-1,371,-1:2,371,-1:20,166,-1:62,371:6,-1,371:3,79,371:18,-1,371:2,-1:11," +
"371:4,-1:4,318,-1,371,-1:2,371,-1:14,167,-1:91,168,-1:55,169,-1:65,170,-1:6" +
"6,172,-1:70,307,-1:74,174,-1:81,176,-1:50,177,-1:94,244,-1:57,178,-1:71,245" +
",-1:60,179,-1:72,180,-1:68,181,-1:62,182,-1:70,266,-1:70,183,-1:77,185,-1:6" +
"5,186,-1:92,261,-1:48,187,-1:66,188,-1:65,190,-1:72,191,-1:91,192,-1:50,194" +
",-1:64,80,-1:73,198,-1:73,249,-1:61,199,-1:72,200,-1:67,251,-1:87,201,-1:56" +
",202,-1:75,252,-1:57,81,-1:67,205,-1:76,206,-1:72,209,-1:70,210,-1:76,253,-" +
"1:53,82,-1:70,211,-1:68,83,-1:68,212,-1:76,213,-1:63,84,-1:73,263,-1:68,214" +
",-1:68,215,-1:65,85,-1:71,219,-1:69,86,-1:73,221,-1:71,256,-1:60,222,-1:99," +
"87,-1:61,223,-1:49,88,-1:73,224,-1:68,225,-1:73,227,-1:79,228,-1:62,89,-1:6" +
"3,229,-1:64,230,-1:79,231,-1:60,232,-1:79,90,-1:61,233,-1:72,234,-1:92,91,-" +
"1:45,235,-1:71,236,-1:68,237,-1:67,238,-1:68,92,-1:93,93,-1:27,1,-1:82,371:" +
"6,-1,105,371:21,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,1" +
"32,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:15,160,-1:70,175" +
",-1:70,184,-1:77,246,-1:67,193,-1:66,197,-1:66,203,-1:72,208,-1:72,204,-1:7" +
"5,255,-1:65,254,-1:60,217,-1:76,218,-1:65,216,-1:70,226,-1:62,371:3,107,371" +
",324,-1,325,371:8,109,371:12,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371," +
"-1:13,371:6,140,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:15," +
"161,-1:71,247,-1:75,248,-1:69,207,-1:65,220,-1:63,371:3,282,371,111,-1,371:" +
"5,283,371:16,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,154," +
"371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:17,189,-1:75,196,-1" +
":59,371:6,-1,371:13,113,371:8,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371" +
",-1:13,371:6,156,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13" +
",371:5,115,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,37" +
"1:6,165,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,-1" +
",371:3,117,371:18,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6" +
",243,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371,332,371" +
":4,-1,371:21,119,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6," +
"171,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,-1,371" +
":22,-1,371:2,-1:11,121,371:3,-1:4,318,-1,371,-1:2,371,-1:13,371:6,173,371:2" +
"2,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,-1,371:10,123,3" +
"71:11,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,195,371:22," +
"-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:2,337,371:2,125,-1," +
"371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:3,127,371:2," +
"-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,-1,371:" +
"22,-1,371:2,-1:11,133,371:3,-1:4,318,-1,371,-1:2,371,-1:13,371:6,-1,371:3,1" +
"35,371:18,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,-1,371:" +
"5,137,371:16,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,-1,1" +
"39,371:21,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:4,141,371" +
",-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,-1,371" +
":9,241,371:12,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,-1," +
"371:13,143,371:8,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:4," +
"145,371,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6" +
",-1,371,147,371:20,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:" +
"6,-1,371:13,151,371:8,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,3" +
"71:5,258,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:" +
"6,-1,371:9,153,371:12,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,3" +
"71:3,155,371:2,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:1" +
"3,371:6,-1,371:7,408,371:14,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-" +
"1:13,371:6,-1,371,157,371:20,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371," +
"-1:13,371:4,265,371,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371" +
",-1:13,371:5,269,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1" +
":13,371:6,-1,371:9,412,371:12,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371" +
",-1:13,371:6,-1,371:7,271,371:14,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2," +
"371,-1:13,371:5,273,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371" +
",-1:13,371:6,-1,371:22,-1,371,275,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:1" +
"3,371:6,-1,371:2,277,371:19,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-" +
"1:13,371:6,-1,371:22,-1,371,279,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13," +
"371:6,-1,371:3,159,371:18,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:" +
"13,371:3,268,371:2,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371," +
"-1:14,260,-1:77,267,-1:69,250,-1:60,371:6,-1,371:19,270,371:2,-1,371:2,-1:1" +
"1,371:4,-1:4,318,-1,371,-1:2,371,-1:22,262,-1:60,371:6,-1,371:13,272,371:8," +
"-1,371:2,-1:11,371,274,371:2,-1:4,318,-1,371,-1:2,371,-1:13,371,320,371:4,-" +
"1,371:5,276,371:16,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:" +
"5,280,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:5,2" +
"81,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:3,284," +
"371:2,-1,328,371:6,285,371:14,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371" +
",-1:13,371:3,286,371:2,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2," +
"371,-1:13,318:6,-1,318:22,-1,318:2,-1:11,318:4,-1:4,318,-1,318,-1:2,318,-1:" +
"13,371:5,333,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13," +
"371:6,-1,371:22,-1,371:2,-1:11,371:2,334,371,-1:4,318,-1,371,-1:2,371,-1:13" +
",371:6,-1,371:6,335,371:15,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1" +
":13,371:6,-1,371:3,377,371:2,336,371:15,-1,371:2,-1:11,371:4,-1:4,318,-1,37" +
"1,-1:2,371,-1:13,371:6,-1,376,371:21,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-" +
"1:2,371,-1:13,371:6,-1,371:13,375,371:8,-1,371:2,-1:11,371:4,-1:4,318,-1,37" +
"1,-1:2,371,-1:13,371:6,-1,371,385,371:20,-1,371:2,-1:11,371:4,-1:4,318,-1,3" +
"71,-1:2,371,-1:13,371:6,-1,371:9,339,371:12,-1,371:2,-1:11,371:4,-1:4,318,-" +
"1,371,-1:2,371,-1:13,371:6,-1,371:16,340,371:5,-1,371:2,-1:11,371:4,-1:4,31" +
"8,-1,371,-1:2,371,-1:13,371,341,371:4,-1,371:22,-1,371:2,-1:11,371:4,-1:4,3" +
"18,-1,371,-1:2,371,-1:13,371:6,-1,371,287,371:20,-1,371:2,-1:11,371:4,-1:4," +
"318,-1,371,-1:2,371,-1:13,371:6,-1,371:3,342,371:18,-1,371:2,-1:11,371:4,-1" +
":4,318,-1,371,-1:2,371,-1:13,371:2,343,371:3,-1,371:22,-1,371:2,-1:11,371:4" +
",-1:4,318,-1,371,-1:2,371,-1:13,371:3,288,371:2,-1,371:22,-1,371:2,-1:11,37" +
"1:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,-1,371:7,380,371:14,-1,371:2,-1:11" +
",371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,-1,371:3,289,371:18,-1,371:2,-1" +
":11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,-1,371:5,290,371:16,-1,371:2" +
",-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,-1,371:6,346,371:15,-1,37" +
"1:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:3,291,371:2,-1,371:22,-1" +
",371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:4,292,371,-1,371:22,-" +
"1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371,384,371:4,-1,371:22," +
"-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,-1,389,371:21,-1," +
"371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,-1,371,293,371:20,-1" +
",371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371,348,371:4,-1,371:22,-" +
"1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:3,350,371:2,-1,371:2" +
"2,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:5,351,-1,371:22,-" +
"1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,-1,371:13,294,371:" +
"8,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:5,295,-1,371:22,-" +
"1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,-1,371:10,354,371:" +
"11,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,-1,371:13,297," +
"371:8,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:3,391,371:2,-" +
"1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:4,298,371," +
"-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,-1,371:" +
"22,-1,371:2,-1:11,371,357,371:2,-1:4,318,-1,371,-1:2,371,-1:13,371:4,381,37" +
"1,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,-1,37" +
"1:10,410,371:11,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,-" +
"1,371:5,359,371:16,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:" +
"3,360,371:2,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,3" +
"71:6,-1,371,299,371:20,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13," +
"371,362,371:4,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13" +
",371:3,300,371:2,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1" +
":13,371:6,-1,364,371:21,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13" +
",371:4,301,371,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:1" +
"3,371:6,-1,371:13,302,371:8,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-" +
"1:13,371:5,365,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:1" +
"3,371:6,-1,371,303,371:20,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:" +
"13,371,366,371:4,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1" +
":13,371:6,-1,371,367,371:20,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-" +
"1:13,371:6,-1,371:3,368,371:18,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,37" +
"1,-1:13,371:4,369,371,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,3" +
"71,-1:13,371:6,-1,371,370,371:20,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2," +
"371,-1:13,371:6,-1,371:13,304,371:8,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1" +
":2,371,-1:13,371:5,305,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2," +
"371,-1:21,308,-1:69,309,-1:61,371:6,-1,338,371:21,-1,371:2,-1:11,371:4,-1:4" +
",318,-1,371,-1:2,371,-1:13,371:6,-1,371:13,401,371:8,-1,371:2,-1:11,371:4,-" +
"1:4,318,-1,371,-1:2,371,-1:13,371:6,-1,371:9,386,371:12,-1,371:2,-1:11,371:" +
"4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,-1,371:16,345,371:5,-1,371:2,-1:11,3" +
"71:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,-1,371:3,344,371:18,-1,371:2,-1:1" +
"1,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371,349,371:4,-1,371:22,-1,371:2,-1:" +
"11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:3,352,371:2,-1,371:22,-1,371:2," +
"-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,-1,371:5,363,371:16,-1,371" +
":2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:5,321,-1,371:5,322,371:16" +
",-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:21,311,-1:61,371:6,-1,371" +
":13,355,371:8,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6,-1," +
"371:3,347,371:18,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371,35" +
"3,371:4,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371:6" +
",-1,371:7,323,371:14,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,37" +
"1:6,-1,371:13,358,371:8,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13" +
",371,356,371:4,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:1" +
"3,371,374,371:4,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:" +
"13,371:6,-1,371:13,361,371:8,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371," +
"-1:13,371:6,-1,326,371:21,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:" +
"13,371:6,-1,371:15,327,371:6,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371," +
"-1:13,371:6,-1,371:5,329,371:16,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,3" +
"71,-1:13,371:5,330,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,371," +
"-1:13,371:6,-1,371:5,400,371:16,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-1:2,3" +
"71,-1:13,371:2,331,371:3,-1,371:22,-1,371:2,-1:11,371:2,378,371,-1:4,318,-1" +
",371,-1:2,371,-1:20,372,-1:69,373,-1:62,371:6,-1,371:3,379,371:18,-1,371:2," +
"-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:13,371,388,371:4,-1,371:22,-1,371:2" +
",-1:11,371:4,-1:4,318,-1,371,-1:2,371,-1:20,383,-1:64,398,-1:69,399,-1:69,4" +
"02,-1:70,403,-1:69,404,-1:66,371:6,406,371:22,-1,371:2,-1:11,371:4,-1:4,318" +
",-1,371,-1:2,371,-1:13,371:6,407,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,37" +
"1,-1:2,371,-1:13,371:5,409,-1,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,371,-" +
"1:2,371,-1:16,405,-1:66,371:6,411,371:22,-1,371:2,-1:11,371:4,-1:4,318,-1,3" +
"71,-1:2,371");

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
                        //skip newline, but reset char counter
			yychar = 0;
			}
					case -3:
						break;
					case 3:
						{
			// skip whitespace
			}
					case -4:
						break;
					case 4:
						{return new Symbol(sym.DIV);}
					case -5:
						break;
					case 5:
						{return new Symbol(sym.RPAREN);}
					case -6:
						break;
					case 6:
						{return new Symbol(sym.MUL);}
					case -7:
						break;
					case 7:
						{return new Symbol(sym.PLUS);}
					case -8:
						break;
					case 8:
						{return new Symbol(sym.MINUS);}
					case -9:
						break;
					case 9:
						{return new Symbol(sym.MOD);}
					case -10:
						break;
					case 10:
						{return new Symbol(sym.COMMA);}
					case -11:
						break;
					case 11:
						{return new Symbol(sym.SEMI);}
					case -12:
						break;
					case 12:
						{return new Symbol(sym.PERIOD);}
					case -13:
						break;
					case 13:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -14:
						break;
					case 14:
						{return new Symbol(sym.A);}
					case -15:
						break;
					case 15:
						{return new Symbol(sym.ANDBIT);}
					case -16:
						break;
					case 16:
						{return new Symbol(sym.ORBIT);}
					case -17:
						break;
					case 17:
						{return new Symbol(sym.NOTBIT);}
					case -18:
						break;
					case 18:
						{return new Symbol(sym.EQUAL);}
					case -19:
						break;
					case 19:
						{return new Symbol(sym.GREATER);}
					case -20:
						break;
					case 20:
						{return new Symbol(sym.LESSER);}
					case -21:
						break;
					case 21:
						{return new Symbol(sym.CONCAT);}
					case -22:
						break;
					case 22:
						{return new Symbol(sym.LBRACK);}
					case -23:
						break;
					case 23:
						{return new Symbol(sym.RBRACK);}
					case -24:
						break;
					case 24:
						{return new Symbol(sym.LPAREN);}
					case -25:
						break;
					case 25:
						{return new Symbol(sym.LBRACE);}
					case -26:
						break;
					case 26:
						{return new Symbol(sym.RBRACE);}
					case -27:
						break;
					case 27:
						{ return new Symbol(sym.INTEGER, new Integer(yytext())); }
					case -28:
						break;
					case 28:
						{ 
                                        // ignore comments
                                        }
					case -29:
						break;
					case 29:
						{return new Symbol(sym.COMBEGIN);}
					case -30:
						break;
					case 30:
						{return new Symbol(sym.COMEND);}
					case -31:
						break;
					case 31:
						{return new Symbol(sym.DOUBLE, new Double(yytext()));}
					case -32:
						break;
					case 32:
						{return new Symbol(sym.AN);}
					case -33:
						break;
					case 33:
						{return new Symbol(sym.AS);}
					case -34:
						break;
					case 34:
						{return new Symbol(sym.OR);}
					case -35:
						break;
					case 35:
						{return new Symbol(sym.IF);}
					case -36:
						break;
					case 36:
						{return new Symbol(sym.ASSIGN);}
					case -37:
						break;
					case 37:
						{return new Symbol(sym.RVECTORBRACK);}
					case -38:
						break;
					case 38:
						{return new Symbol(sym.GOE);}
					case -39:
						break;
					case 39:
						{return new Symbol(sym.LOE);}
					case -40:
						break;
					case 40:
						{return new Symbol(sym.NOTEQ);}
					case -41:
						break;
					case 41:
						{return new Symbol(sym.LVECTORBRACK);}
					case -42:
						break;
					case 42:
						{return new Symbol(sym.TRUE, new Boolean(true) );}
					case -43:
						break;
					case 43:
						{return new Symbol(sym.NIL, new Nil() );}
					case -44:
						break;
					case 44:
						{return new Symbol(sym.FALSE, new Boolean(false) );}
					case -45:
						break;
					case 45:
						{ return new Symbol(sym.STRING, new String( yytext().substring(1,yytext().length()-1 )  ) );}
					case -46:
						break;
					case 46:
						{ return new Symbol(sym.CHAR, new Char(yytext()));}
					case -47:
						break;
					case 47:
						{return new Symbol(sym.ELIPSE);}
					case -48:
						break;
					case 48:
						{return new Symbol(sym.AND);}
					case -49:
						break;
					case 49:
						{return new Symbol(sym.NOT);}
					case -50:
						break;
					case 50:
						{return new Symbol(sym.CAR);}
					case -51:
						break;
					case 51:
						{return new Symbol(sym.CDR);}
					case -52:
						break;
					case 52:
						{return new Symbol(sym.LET);}
					case -53:
						break;
					case 53:
						{return new Symbol(sym.TRUE_QUERY_SELF_DECLARATION_SUFFIX0);}
					case -54:
						break;
					case 54:
						{return new Symbol(sym.THEN);}
					case -55:
						break;
					case 55:
						{return new Symbol(sym.ELSE);}
					case -56:
						break;
					case 56:
						{return new Symbol(sym.EQV);}
					case -57:
						break;
					case 57:
						{return new Symbol(sym.SIZE);}
					case -58:
						break;
					case 58:
						{return new Symbol(sym.WEIGHT_QUERY_SUFFIX1);}
					case -59:
						break;
					case 59:
						{return new Symbol(sym.WEIGHT_QUERY_SUFFIX0);}
					case -60:
						break;
					case 60:
						{return new Symbol(sym.READ);}
					case -61:
						break;
					case 61:
						{return new Symbol(sym.CASE);}
					case -62:
						break;
					case 62:
						{return new Symbol(sym.CALL);}
					case -63:
						break;
					case 63:
						{return new Symbol(sym.LAZY);}
					case -64:
						break;
					case 64:
						{return new Symbol(sym.LIST);}
					case -65:
						break;
					case 65:
						{return new Symbol(sym.PAIR);}
					case -66:
						break;
					case 66:
						{return new Symbol(sym.PROC);}
					case -67:
						break;
					case 67:
						{return new Symbol(sym.STATE);}
					case -68:
						break;
					case 68:
						{return new Symbol(sym.PAIRBOOL);}
					case -69:
						break;
					case 69:
						{return new Symbol(sym.OBJEQUAL);}
					case -70:
						break;
					case 70:
						{return new Symbol(sym.SUBSTR);}
					case -71:
						break;
					case 71:
						{return new Symbol(sym.DEFINE);}
					case -72:
						break;
					case 72:
						{return new Symbol(sym.WEIGHT_QUERY_SUFFIX2);}
					case -73:
						break;
					case 73:
						{return new Symbol(sym.TRUE_QUERY_SELF_DECLARATION_SUFFIX1);}
					case -74:
						break;
					case 74:
						{return new Symbol(sym.TRUE_QUERY_SELF_WEIGHT_DECLARATION_SUFFIX);}
					case -75:
						break;
					case 75:
						{return new Symbol(sym.TRUE_QUERY_SELF_DECLARATION_PREFIX);}
					case -76:
						break;
					case 76:
						{return new Symbol(sym.DISPLAY);}
					case -77:
						break;
					case 77:
						{return new Symbol(sym.READINT);}
					case -78:
						break;
					case 78:
						{return new Symbol(sym.TRUE_QUERY_SELF_WEIGHT_DECLARATION_PREFIX);}
					case -79:
						break;
					case 79:
						{return new Symbol(sym.PHILOSOPHICAL_DEFINITION_QUERY_TARGET_0);}
					case -80:
						break;
					case 80:
						{return new Symbol(sym.QUOTE);}
					case -81:
						break;
					case 81:
						{return new Symbol(sym.DIFFER);}
					case -82:
						break;
					case 82:
						{return new Symbol(sym.SUMMATE);}
					case -83:
						break;
					case 83:
						{return new Symbol(sym.PRODUCE);}
					case -84:
						break;
					case 84:
						{return new Symbol(sym.EXPOUND);}
					case -85:
						break;
					case 85:
						{return new Symbol(sym.SIMULATION_QUERY_TARGET_1);}
					case -86:
						break;
					case 86:
						{return new Symbol(sym.MODULATE);}
					case -87:
						break;
					case 87:
						{return new Symbol(sym.VISUALLY_DISPLAY);}
					case -88:
						break;
					case 88:
						{return new Symbol(sym.SIMULATION_QUERY_TARGET_0);}
					case -89:
						break;
					case 89:
						{return new Symbol(sym.SIMULATION_QUERY_PREFIX_0);}
					case -90:
						break;
					case 90:
						{return new Symbol(sym.DEFINITION_QUERY_PREFIX_0);}
					case -91:
						break;
					case 91:
						{return new Symbol(sym.SUBSEQUENTLY_DISPLAY);}
					case -92:
						break;
					case 92:
						{return new Symbol(sym.WEIGHT_QUERY_PREFIX);}
					case -93:
						break;
					case 93:
						{return new Symbol(sym.OUTPUT_SOURCE_PREFIX);}
					case -94:
						break;
					case 95:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -95:
						break;
					case 96:
						{ 
                                        // ignore comments
                                        }
					case -96:
						break;
					case 97:
						{ return new Symbol(sym.CHAR, new Char(yytext()));}
					case -97:
						break;
					case 99:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -98:
						break;
					case 100:
						{ return new Symbol(sym.CHAR, new Char(yytext()));}
					case -99:
						break;
					case 102:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -100:
						break;
					case 103:
						{ return new Symbol(sym.CHAR, new Char(yytext()));}
					case -101:
						break;
					case 105:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -102:
						break;
					case 107:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -103:
						break;
					case 109:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -104:
						break;
					case 111:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -105:
						break;
					case 113:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -106:
						break;
					case 115:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -107:
						break;
					case 117:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -108:
						break;
					case 119:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -109:
						break;
					case 121:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -110:
						break;
					case 123:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -111:
						break;
					case 125:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -112:
						break;
					case 127:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -113:
						break;
					case 129:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -114:
						break;
					case 131:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -115:
						break;
					case 133:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -116:
						break;
					case 135:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -117:
						break;
					case 137:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -118:
						break;
					case 139:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -119:
						break;
					case 141:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -120:
						break;
					case 143:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -121:
						break;
					case 145:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -122:
						break;
					case 147:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -123:
						break;
					case 149:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -124:
						break;
					case 151:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -125:
						break;
					case 153:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -126:
						break;
					case 155:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -127:
						break;
					case 157:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -128:
						break;
					case 159:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -129:
						break;
					case 240:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -130:
						break;
					case 241:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -131:
						break;
					case 257:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -132:
						break;
					case 258:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -133:
						break;
					case 264:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -134:
						break;
					case 265:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -135:
						break;
					case 268:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -136:
						break;
					case 269:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -137:
						break;
					case 270:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -138:
						break;
					case 271:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -139:
						break;
					case 272:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -140:
						break;
					case 273:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -141:
						break;
					case 274:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -142:
						break;
					case 275:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -143:
						break;
					case 276:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -144:
						break;
					case 277:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -145:
						break;
					case 278:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -146:
						break;
					case 279:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -147:
						break;
					case 280:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -148:
						break;
					case 281:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -149:
						break;
					case 282:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -150:
						break;
					case 283:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -151:
						break;
					case 284:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -152:
						break;
					case 285:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -153:
						break;
					case 286:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -154:
						break;
					case 287:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -155:
						break;
					case 288:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -156:
						break;
					case 289:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -157:
						break;
					case 290:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -158:
						break;
					case 291:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -159:
						break;
					case 292:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -160:
						break;
					case 293:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -161:
						break;
					case 294:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -162:
						break;
					case 295:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -163:
						break;
					case 296:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -164:
						break;
					case 297:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -165:
						break;
					case 298:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -166:
						break;
					case 299:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -167:
						break;
					case 300:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -168:
						break;
					case 301:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -169:
						break;
					case 302:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -170:
						break;
					case 303:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -171:
						break;
					case 304:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -172:
						break;
					case 305:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -173:
						break;
					case 306:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -174:
						break;
					case 310:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -175:
						break;
					case 312:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -176:
						break;
					case 313:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -177:
						break;
					case 314:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -178:
						break;
					case 315:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -179:
						break;
					case 316:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -180:
						break;
					case 317:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -181:
						break;
					case 318:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -182:
						break;
					case 319:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -183:
						break;
					case 320:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -184:
						break;
					case 321:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -185:
						break;
					case 322:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -186:
						break;
					case 323:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -187:
						break;
					case 324:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -188:
						break;
					case 325:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -189:
						break;
					case 326:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -190:
						break;
					case 327:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -191:
						break;
					case 328:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -192:
						break;
					case 329:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -193:
						break;
					case 330:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -194:
						break;
					case 331:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -195:
						break;
					case 332:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -196:
						break;
					case 333:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -197:
						break;
					case 334:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -198:
						break;
					case 335:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -199:
						break;
					case 336:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -200:
						break;
					case 337:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -201:
						break;
					case 338:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -202:
						break;
					case 339:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -203:
						break;
					case 340:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -204:
						break;
					case 341:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -205:
						break;
					case 342:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -206:
						break;
					case 343:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -207:
						break;
					case 344:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -208:
						break;
					case 345:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -209:
						break;
					case 346:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -210:
						break;
					case 347:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -211:
						break;
					case 348:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -212:
						break;
					case 349:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -213:
						break;
					case 350:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -214:
						break;
					case 351:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -215:
						break;
					case 352:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -216:
						break;
					case 353:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -217:
						break;
					case 354:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -218:
						break;
					case 355:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -219:
						break;
					case 356:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -220:
						break;
					case 357:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -221:
						break;
					case 358:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -222:
						break;
					case 359:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -223:
						break;
					case 360:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -224:
						break;
					case 361:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -225:
						break;
					case 362:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -226:
						break;
					case 363:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -227:
						break;
					case 364:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -228:
						break;
					case 365:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -229:
						break;
					case 366:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -230:
						break;
					case 367:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -231:
						break;
					case 368:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -232:
						break;
					case 369:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -233:
						break;
					case 370:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -234:
						break;
					case 371:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -235:
						break;
					case 374:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -236:
						break;
					case 375:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -237:
						break;
					case 376:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -238:
						break;
					case 377:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -239:
						break;
					case 378:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -240:
						break;
					case 379:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -241:
						break;
					case 380:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -242:
						break;
					case 381:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -243:
						break;
					case 382:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -244:
						break;
					case 384:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -245:
						break;
					case 385:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -246:
						break;
					case 386:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -247:
						break;
					case 387:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -248:
						break;
					case 388:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -249:
						break;
					case 389:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -250:
						break;
					case 390:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -251:
						break;
					case 391:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -252:
						break;
					case 392:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -253:
						break;
					case 393:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -254:
						break;
					case 394:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -255:
						break;
					case 395:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -256:
						break;
					case 396:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -257:
						break;
					case 397:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -258:
						break;
					case 400:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -259:
						break;
					case 401:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -260:
						break;
					case 408:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -261:
						break;
					case 409:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -262:
						break;
					case 410:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -263:
						break;
					case 412:
						{ return new Symbol(sym.VARIABLE, new String(yytext()));}
					case -264:
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
