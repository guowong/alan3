DO NOT TRANSLATE OR LOCALIZE.

%% The following software may be included in this product:  zlib. Use of any of this software is governed by the terms of the license below: 

zlib.h -- interface of the 'zlib' general purpose compression library
  version 1.1.3, July 9th, 1998

  Copyright (C) 1995-1998 Jean-loup Gailly and Mark Adler

  This software is provided 'as-is', without any express or implied
  warranty.  In no event will the authors be held liable for any damages
  arising from the use of this software.

  Permission is granted to anyone to use this software for any purpose,
  including commercial applications, and to alter it and redistribute it
  freely, subject to the following restrictions:

  1. The origin of this software must not be misrepresented; you must not
     claim that you wrote the original software. If you use this software
     in a product, an acknowledgment in the product documentation would be
     appreciated but is not required.
  2. Altered source versions must be plainly marked as such, and must not be
     misrepresented as being the original software.
  3. This notice may not be removed or altered from any source distribution.

  Jean-loup Gailly        Mark Adler
  jloup@gzip.org          madler@alumni.caltech.edu


  The data format used by the zlib library is described by RFCs (Request for
  Comments) 1950 to 1952 in the files ftp://ds.internic.net/rfc/rfc1950.txt
  (zlib format), rfc1951.txt (deflate format) and rfc1952.txt (gzip format
%% The following software may be included in this product:  Jpeg . Use of any of this software is governed by the terms of the license below: 

LEGAL ISSUES
============

In plain English:

1. We don't promise that this software works.  (But if you find any bugs,
   please let us know!)
2. You can use this software for whatever you want.  You don't have to pay us.
3. You may not pretend that you wrote this software.  If you use it in a
   program, you must acknowledge somewhere in your documentation that
   you've used the IJG code.

In legalese:

The authors make NO WARRANTY or representation, either express or implied,
with respect to this software, its quality, accuracy, merchantability, or
fitness for a particular purpose.  This software is provided "AS IS", and you,
its user, assume the entire risk as to its quality and accuracy.

This software is copyright (C) 1991-1998, Thomas G. Lane.
All Rights Reserved except as specified below.

Permission is hereby granted to use, copy, modify, and distribute this
software (or portions thereof) for any purpose, without fee, subject to these
conditions:
(1) If any part of the source code for this software is distributed, then this
README file must be included, with this copyright and no-warranty notice
unaltered; and any additions, deletions, or changes to the original files
must be clearly indicated in accompanying documentation.
(2) If only executable code is distributed, then the accompanying
documentation must state that "this software is based in part on the work of
the Independent JPEG Group".
(3) Permission for use of this software is granted only if the user accepts
full responsibility for any undesirable consequences; the authors accept
NO LIABILITY for damages of any kind.

These conditions apply to any software derived from or based on the IJG code,
not just to the unmodified library.  If you use our work, you ought to
acknowledge us.

Permission is NOT granted for the use of any IJG author's name or company name
in advertising or publicity relating to this software or products derived from
it.  This software may be referred to only as "the Independent JPEG Group's
software".

We specifically permit and encourage the use of this software as the basis of
commercial products, provided that all warranty or liability claims are
assumed by the product vendor.


ansi2knr.c is included in this distribution by permission of L. Peter Deutsch,
sole proprietor of its copyright holder, Aladdin Enterprises of Menlo Park, CA.
ansi2knr.c is NOT covered by the above copyright and conditions, but instead
by the usual distribution terms of the Free Software Foundation; principally,
that you must include source code if you redistribute it.  (See the file
ansi2knr.c for full details.)  However, since ansi2knr.c is not needed as part
of any program generated from the IJG code, this does not limit you more than
the foregoing paragraphs do.

The Unix configuration script "configure" was produced with GNU Autoconf.
It is copyright by the Free Software Foundation but is freely distributable.
The same holds for its supporting scripts (config.guess, config.sub,
ltconfig, ltmain.sh).  Another support script, install-sh, is copyright
by M.I.T. but is also freely distributable.

It appears that the arithmetic coding option of the JPEG spec is covered by
patents owned by IBM, AT&T, and Mitsubishi.  Hence arithmetic coding cannot
legally be used without obtaining one or more licenses.  For this reason,
support for arithmetic coding has been removed from the free JPEG software.
(Since arithmetic coding provides only a marginal gain over the unpatented
Huffman mode, it is unlikely that very many implementations will support it.)
So far as we are aware, there are no patent restrictions on the remaining
code.

The IJG distribution formerly included code to read and write GIF files.
To avoid entanglement with the Unisys LZW patent, GIF reading support has
been removed altogether, and the GIF writer has been simplified to produce
"uncompressed GIFs".  This technique does not use the LZW algorithm; the
resulting GIF files are larger than usual, but are readable by all standard
GIF decoders.

We are required to state that
    "The Graphics Interchange Format(c) is the Copyright property of
    CompuServe Incorporated.  GIF(sm) is a Service Mark property of
    CompuServe Incorporated."

%% The following software may be included in this product:  DES and 3DES. Use of any of this software is governed by the terms of the license below: 

ACME Labs Freeware License

All the free software available on the ACME Labs web site has a copyright notice
like this one:

Copyright ýýýýýýýýýýýýýýýý 2000 by Jef Poskanzer .  All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions
are met:
1. Redistributions of source code must retain the above copyright
   notice, this list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright
   notice, this list of conditions and the following disclaimer in the
   documentation and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS'' AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
SUCH DAMAGE.

So what does this legalese mean? This is a modified version of the BSD license.
You may be more familiar with the Gnu Public License, since it gets a lot of
press. Well, forget about that. The BSD license is very different. While the Gnu
license puts all sorts of restrictions on what you can do with the software,
BSD-style licenses say "Hey, do what you like, we don't care. Just let people
know we wrote it, and don't sue us." That's really all there is to it. The first
paragraph with the numbered items says you can do what you like with the code,
as long as you keep our name on it. The original BSD license has a couple more
provisions there, we got rid of those. The second paragraph, all in capital
letters, is a standard legal boilerplate notification that tries to make it
difficult for anyone to successfully sue us over the software.

By the way, FreshMeat has a nice list of different types of freeware licenses.
There are quite a few besides BSD and GPL. There's also the OpenSource.org list
of open source licenses, which is not quite the same as the freeware licenses.

Here are some questions people ask about the license:

"Can I use your software in a freeware product?"
    Sure. 
"Can I use your software in a commercial product? Do I have to pay you?"
    Yes you can, and no you don't have to, although we certainly won't object to
any checks you care to send our way. 
"If I use your software as part of my product, do I have to include your source
code? Or my source code?""
    No. You're thinking of the Gnu license. Our license just says that if you
distribute our source code then you must leave in the license text; but you are
free to use it in binary-only form if you like, and put the license text in your
documentation instead. 
"Can I make my own version of your software?"
    Of course. 
"Can I distribute my own version of your software?"
    Yes, although we'd prefer that you didn't. Having multiple versions of the
same thing floating around is inherently distasteful. If you absolutely must
distribute your own modified version of our software, please name it something
else to avoid confusing people. 
"Would you please send me a signed disclaimer, so my company's lawyers are happy?"
    No. The license in the source code is sufficient. 
"We don't want to put your license in our documentation or distribute your
source, can we use your software anyway?"
    We occasionally grant special permission for this sort of thing. A donation
to ACME Labs will probably improve the chances. 
%% The following software may be included in this product:  Cryptix. Use of any of this software is governed by the terms of the license below: 

Cryptix General License

Copyright } 1995-2001 The Cryptix Foundation Limited. All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

1.Redistributions of source code must retain the copyright notice, this list of
conditions and the following disclaimer. 
2.Redistributions in binary form must reproduce the above copyright notice, this
list of  conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution. 

THIS SOFTWARE IS PROVIDED BY THE CRYPTIX FOUNDATION LIMITED AND
CONTRIBUTORS ``AS IS'' AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING,
BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE CRYPTIX
FOUNDATION LIMITED OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
%% The following software may be included in this product:  winp. Use of any of this software is governed by the terms of the license below: 

The MIT License

Copyright (c)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
%% The following software may be included in this product:  gstreamer. Use of any of this software is governed by the terms of the license below: 

GNU LIBRARY GENERAL PUBLIC LICENSE
                       Version 2, June 1991

 Copyright (C) 1991 Free Software Foundation, Inc.
 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 Everyone is permitted to copy and distribute verbatim copies
 of this license document, but changing it is not allowed.

[This is the first released version of the library GPL.  It is
 numbered 2 because it goes with version 2 of the ordinary GPL.]

                            Preamble

  The licenses for most software are designed to take away your
freedom to share and change it.  By contrast, the GNU General Public
Licenses are intended to guarantee your freedom to share and change
free software--to make sure the software is free for all its users.

  This license, the Library General Public License, applies to some
specially designated Free Software Foundation software, and to any
other libraries whose authors decide to use it.  You can use it for
your libraries, too.

  When we speak of free software, we are referring to freedom, not
price.  Our General Public Licenses are designed to make sure that you
have the freedom to distribute copies of free software (and charge for
this service if you wish), that you receive source code or can get it
if you want it, that you can change the software or use pieces of it
in new free programs; and that you know you can do these things.

  To protect your rights, we need to make restrictions that forbid
anyone to deny you these rights or to ask you to surrender the rights.
These restrictions translate to certain responsibilities for you if
you distribute copies of the library, or if you modify it.

  For example, if you distribute copies of the library, whether gratis
or for a fee, you must give the recipients all the rights that we gave
you.  You must make sure that they, too, receive or can get the source
code.  If you link a program with the library, you must provide
complete object files to the recipients so that they can relink them
with the library, after making changes to the library and recompiling
it.  And you must show them these terms so they know their rights.

  Our method of protecting your rights has two steps: (1) copyright
the library, and (2) offer you this license which gives you legal
permission to copy, distribute and/or modify the library.

  Also, for each distributor's protection, we want to make certain
that everyone understands that there is no warranty for this free
library.  If the library is modified by someone else and passed on, we
want its recipients to know that what they have is not the original
version, so that any problems introduced by others will not reflect on
the original authors' reputations.

  Finally, any free program is threatened constantly by software
patents.  We wish to avoid the danger that companies distributing free
software will individually obtain patent licenses, thus in effect
transforming the program into proprietary software.  To prevent this,
we have made it clear that any patent must be licensed for everyone's
free use or not licensed at all.

  Most GNU software, including some libraries, is covered by the ordinary
GNU General Public License, which was designed for utility programs.  This
license, the GNU Library General Public License, applies to certain
designated libraries.  This license is quite different from the ordinary
one; be sure to read it in full, and don't assume that anything in it is
the same as in the ordinary license.

  The reason we have a separate public license for some libraries is that
they blur the distinction we usually make between modifying or adding to a
program and simply using it.  Linking a program with a library, without
changing the library, is in some sense simply using the library, and is
analogous to running a utility program or application program.  However, in
a textual and legal sense, the linked executable is a combined work, a
derivative of the original library, and the ordinary General Public License
treats it as such.

  Because of this blurred distinction, using the ordinary General
Public License for libraries did not effectively promote software
sharing, because most developers did not use the libraries.  We
concluded that weaker conditions might promote sharing better.

  However, unrestricted linking of non-free programs would deprive the
users of those programs of all benefit from the free status of the
libraries themselves.  This Library General Public License is intended to
permit developers of non-free programs to use free libraries, while
preserving your freedom as a user of such programs to change the free
libraries that are incorporated in them.  (We have not seen how to achieve
this as regards changes in header files, but we have achieved it as regards
changes in the actual functions of the Library.)  The hope is that this
will lead to faster development of free libraries.

  The precise terms and conditions for copying, distribution and
modification follow.  Pay close attention to the difference between a
"work based on the library" and a "work that uses the library".  The
former contains code derived from the library, while the latter only
works together with the library.

  Note that it is possible for a library to be covered by the ordinary
General Public License rather than by this special one.

                  GNU LIBRARY GENERAL PUBLIC LICENSE
   TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION

  0. This License Agreement applies to any software library which
contains a notice placed by the copyright holder or other authorized
party saying it may be distributed under the terms of this Library
General Public License (also called "this License").  Each licensee is
addressed as "you".

  A "library" means a collection of software functions and/or data
prepared so as to be conveniently linked with application programs
(which use some of those functions and data) to form executables.

  The "Library", below, refers to any such software library or work
which has been distributed under these terms.  A "work based on the
Library" means either the Library or any derivative work under
copyright law: that is to say, a work containing the Library or a
portion of it, either verbatim or with modifications and/or translated
straightforwardly into another language.  (Hereinafter, translation is
included without limitation in the term "modification".)

  "Source code" for a work means the preferred form of the work for
making modifications to it.  For a library, complete source code means
all the source code for all modules it contains, plus any associated
interface definition files, plus the scripts used to control compilation
and installation of the library.

  Activities other than copying, distribution and modification are not
covered by this License; they are outside its scope.  The act of
running a program using the Library is not restricted, and output from
such a program is covered only if its contents constitute a work based
on the Library (independent of the use of the Library in a tool for
writing it).  Whether that is true depends on what the Library does
and what the program that uses the Library does.
  
  1. You may copy and distribute verbatim copies of the Library's
complete source code as you receive it, in any medium, provided that
you conspicuously and appropriately publish on each copy an
appropriate copyright notice and disclaimer of warranty; keep intact
all the notices that refer to this License and to the absence of any
warranty; and distribute a copy of this License along with the
Library.

  You may charge a fee for the physical act of transferring a copy,
and you may at your option offer warranty protection in exchange for a
fee.

  2. You may modify your copy or copies of the Library or any portion
of it, thus forming a work based on the Library, and copy and
distribute such modifications or work under the terms of Section 1
above, provided that you also meet all of these conditions:

    a) The modified work must itself be a software library.

    b) You must cause the files modified to carry prominent notices
    stating that you changed the files and the date of any change.

    c) You must cause the whole of the work to be licensed at no
    charge to all third parties under the terms of this License.

    d) If a facility in the modified Library refers to a function or a
    table of data to be supplied by an application program that uses
    the facility, other than as an argument passed when the facility
    is invoked, then you must make a good faith effort to ensure that,
    in the event an application does not supply such function or
    table, the facility still operates, and performs whatever part of
    its purpose remains meaningful.

    (For example, a function in a library to compute square roots has
    a purpose that is entirely well-defined independent of the
    application.  Therefore, Subsection 2d requires that any
    application-supplied function or table used by this function must
    be optional: if the application does not supply it, the square
    root function must still compute square roots.)

These requirements apply to the modified work as a whole.  If
identifiable sections of that work are not derived from the Library,
and can be reasonably considered independent and separate works in
themselves, then this License, and its terms, do not apply to those
sections when you distribute them as separate works.  But when you
distribute the same sections as part of a whole which is a work based
on the Library, the distribution of the whole must be on the terms of
this License, whose permissions for other licensees extend to the
entire whole, and thus to each and every part regardless of who wrote
it.

Thus, it is not the intent of this section to claim rights or contest
your rights to work written entirely by you; rather, the intent is to
exercise the right to control the distribution of derivative or
collective works based on the Library.

In addition, mere aggregation of another work not based on the Library
with the Library (or with a work based on the Library) on a volume of
a storage or distribution medium does not bring the other work under
the scope of this License.

  3. You may opt to apply the terms of the ordinary GNU General Public
License instead of this License to a given copy of the Library.  To do
this, you must alter all the notices that refer to this License, so
that they refer to the ordinary GNU General Public License, version 2,
instead of to this License.  (If a newer version than version 2 of the
ordinary GNU General Public License has appeared, then you can specify
that version instead if you wish.)  Do not make any other change in
these notices.

  Once this change is made in a given copy, it is irreversible for
that copy, so the ordinary GNU General Public License applies to all
subsequent copies and derivative works made from that copy.

  This option is useful when you wish to copy part of the code of
the Library into a program that is not a library.

  4. You may copy and distribute the Library (or a portion or
derivative of it, under Section 2) in object code or executable form
under the terms of Sections 1 and 2 above provided that you accompany
it with the complete corresponding machine-readable source code, which
must be distributed under the terms of Sections 1 and 2 above on a
medium customarily used for software interchange.

  If distribution of object code is made by offering access to copy
from a designated place, then offering equivalent access to copy the
source code from the same place satisfies the requirement to
distribute the source code, even though third parties are not
compelled to copy the source along with the object code.

  5. A program that contains no derivative of any portion of the
Library, but is designed to work with the Library by being compiled or
linked with it, is called a "work that uses the Library".  Such a
work, in isolation, is not a derivative work of the Library, and
therefore falls outside the scope of this License.

  However, linking a "work that uses the Library" with the Library
creates an executable that is a derivative of the Library (because it
contains portions of the Library), rather than a "work that uses the
library".  The executable is therefore covered by this License.
Section 6 states terms for distribution of such executables.

  When a "work that uses the Library" uses material from a header file
that is part of the Library, the object code for the work may be a
derivative work of the Library even though the source code is not.
Whether this is true is especially significant if the work can be
linked without the Library, or if the work is itself a library.  The
threshold for this to be true is not precisely defined by law.

  If such an object file uses only numerical parameters, data
structure layouts and accessors, and small macros and small inline
functions (ten lines or less in length), then the use of the object
file is unrestricted, regardless of whether it is legally a derivative
work.  (Executables containing this object code plus portions of the
Library will still fall under Section 6.)

  Otherwise, if the work is a derivative of the Library, you may
distribute the object code for the work under the terms of Section 6.
Any executables containing that work also fall under Section 6,
whether or not they are linked directly with the Library itself.

  6. As an exception to the Sections above, you may also compile or
link a "work that uses the Library" with the Library to produce a
work containing portions of the Library, and distribute that work
under terms of your choice, provided that the terms permit
modification of the work for the customer's own use and reverse
engineering for debugging such modifications.

  You must give prominent notice with each copy of the work that the
Library is used in it and that the Library and its use are covered by
this License.  You must supply a copy of this License.  If the work
during execution displays copyright notices, you must include the
copyright notice for the Library among them, as well as a reference
directing the user to the copy of this License.  Also, you must do one
of these things:

    a) Accompany the work with the complete corresponding
    machine-readable source code for the Library including whatever
    changes were used in the work (which must be distributed under
    Sections 1 and 2 above); and, if the work is an executable linked
    with the Library, with the complete machine-readable "work that
    uses the Library", as object code and/or source code, so that the
    user can modify the Library and then relink to produce a modified
    executable containing the modified Library.  (It is understood
    that the user who changes the contents of definitions files in the
    Library will not necessarily be able to recompile the application
    to use the modified definitions.)

    b) Accompany the work with a written offer, valid for at
    least three years, to give the same user the materials
    specified in Subsection 6a, above, for a charge no more
    than the cost of performing this distribution.

    c) If distribution of the work is made by offering access to copy
    from a designated place, offer equivalent access to copy the above
    specified materials from the same place.

    d) Verify that the user has already received a copy of these
    materials or that you have already sent this user a copy.

  For an executable, the required form of the "work that uses the
Library" must include any data and utility programs needed for
reproducing the executable from it.  However, as a special exception,
the source code distributed need not include anything that is normally
distributed (in either source or binary form) with the major
components (compiler, kernel, and so on) of the operating system on
which the executable runs, unless that component itself accompanies
the executable.

  It may happen that this requirement contradicts the license
restrictions of other proprietary libraries that do not normally
accompany the operating system.  Such a contradiction means you cannot
use both them and the Library together in an executable that you
distribute.

  7. You may place library facilities that are a work based on the
Library side-by-side in a single library together with other library
facilities not covered by this License, and distribute such a combined
library, provided that the separate distribution of the work based on
the Library and of the other library facilities is otherwise
permitted, and provided that you do these two things:

    a) Accompany the combined library with a copy of the same work
    based on the Library, uncombined with any other library
    facilities.  This must be distributed under the terms of the
    Sections above.

    b) Give prominent notice with the combined library of the fact
    that part of it is a work based on the Library, and explaining
    where to find the accompanying uncombined form of the same work.

  8. You may not copy, modify, sublicense, link with, or distribute
the Library except as expressly provided under this License.  Any
attempt otherwise to copy, modify, sublicense, link with, or
distribute the Library is void, and will automatically terminate your
rights under this License.  However, parties who have received copies,
or rights, from you under this License will not have their licenses
terminated so long as such parties remain in full compliance.

  9. You are not required to accept this License, since you have not
signed it.  However, nothing else grants you permission to modify or
distribute the Library or its derivative works.  These actions are
prohibited by law if you do not accept this License.  Therefore, by
modifying or distributing the Library (or any work based on the
Library), you indicate your acceptance of this License to do so, and
all its terms and conditions for copying, distributing or modifying
the Library or works based on it.

  10. Each time you redistribute the Library (or any work based on the
Library), the recipient automatically receives a license from the
original licensor to copy, distribute, link with or modify the Library
subject to these terms and conditions.  You may not impose any further
restrictions on the recipients' exercise of the rights granted herein.
You are not responsible for enforcing compliance by third parties to
this License.

  11. If, as a consequence of a court judgment or allegation of patent
infringement or for any other reason (not limited to patent issues),
conditions are imposed on you (whether by court order, agreement or
otherwise) that contradict the conditions of this License, they do not
excuse you from the conditions of this License.  If you cannot
distribute so as to satisfy simultaneously your obligations under this
License and any other pertinent obligations, then as a consequence you
may not distribute the Library at all.  For example, if a patent
license would not permit royalty-free redistribution of the Library by
all those who receive copies directly or indirectly through you, then
the only way you could satisfy both it and this License would be to
refrain entirely from distribution of the Library.

If any portion of this section is held invalid or unenforceable under any
particular circumstance, the balance of the section is intended to apply,
and the section as a whole is intended to apply in other circumstances.

It is not the purpose of this section to induce you to infringe any
patents or other property right claims or to contest validity of any
such claims; this section has the sole purpose of protecting the
integrity of the free software distribution system which is
implemented by public license practices.  Many people have made
generous contributions to the wide range of software distributed
through that system in reliance on consistent application of that
system; it is up to the author/donor to decide if he or she is willing
to distribute software through any other system and a licensee cannot
impose that choice.

This section is intended to make thoroughly clear what is believed to
be a consequence of the rest of this License.

  12. If the distribution and/or use of the Library is restricted in
certain countries either by patents or by copyrighted interfaces, the
original copyright holder who places the Library under this License may add
an explicit geographical distribution limitation excluding those countries,
so that distribution is permitted only in or among countries not thus
excluded.  In such case, this License incorporates the limitation as if
written in the body of this License.

  13. The Free Software Foundation may publish revised and/or new
versions of the Library General Public License from time to time.
Such new versions will be similar in spirit to the present version,
but may differ in detail to address new problems or concerns.

Each version is given a distinguishing version number.  If the Library
specifies a version number of this License which applies to it and
"any later version", you have the option of following the terms and
conditions either of that version or of any later version published by
the Free Software Foundation.  If the Library does not specify a
license version number, you may choose any version ever published by
the Free Software Foundation.

  14. If you wish to incorporate parts of the Library into other free
programs whose distribution conditions are incompatible with these,
write to the author to ask for permission.  For software which is
copyrighted by the Free Software Foundation, write to the Free
Software Foundation; we sometimes make exceptions for this.  Our
decision will be guided by the two goals of preserving the free status
of all derivatives of our free software and of promoting the sharing
and reuse of software generally.

                            NO WARRANTY

  15. BECAUSE THE LIBRARY IS LICENSED FREE OF CHARGE, THERE IS NO
WARRANTY FOR THE LIBRARY, TO THE EXTENT PERMITTED BY APPLICABLE LAW.
EXCEPT WHEN OTHERWISE STATED IN WRITING THE COPYRIGHT HOLDERS AND/OR
OTHER PARTIES PROVIDE THE LIBRARY "AS IS" WITHOUT WARRANTY OF ANY
KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
PURPOSE.  THE ENTIRE RISK AS TO THE QUALITY AND PERFORMANCE OF THE
LIBRARY IS WITH YOU.  SHOULD THE LIBRARY PROVE DEFECTIVE, YOU ASSUME
THE COST OF ALL NECESSARY SERVICING, REPAIR OR CORRECTION.

  16. IN NO EVENT UNLESS REQUIRED BY APPLICABLE LAW OR AGREED TO IN
WRITING WILL ANY COPYRIGHT HOLDER, OR ANY OTHER PARTY WHO MAY MODIFY
AND/OR REDISTRIBUTE THE LIBRARY AS PERMITTED ABOVE, BE LIABLE TO YOU
FOR DAMAGES, INCLUDING ANY GENERAL, SPECIAL, INCIDENTAL OR
CONSEQUENTIAL DAMAGES ARISING OUT OF THE USE OR INABILITY TO USE THE
LIBRARY (INCLUDING BUT NOT LIMITED TO LOSS OF DATA OR DATA BEING
RENDERED INACCURATE OR LOSSES SUSTAINED BY YOU OR THIRD PARTIES OR A
FAILURE OF THE LIBRARY TO OPERATE WITH ANY OTHER SOFTWARE), EVEN IF
SUCH HOLDER OR OTHER PARTY HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH
DAMAGES.
%% The following software may be included in this product:  msvcr80.dll from Microsoft C++. Use of any of this software is governed by the terms of the license below: 

END-USER LICENSE AGREEMENT FOR MICROSOFT SOFTWARE

IMPORTANT-READ CAREFULLY: This Microsoft End-User License Agreement (''EULA'')
is a legal agreement between you (either an individual or a single entity) and
Microsoft Corporation for the Microsoft software product(s) accompanying this
EULA, which include(s) computer software and may include "online" or electronic
documentation, associated media, and printed materials (''SOFTWARE PRODUCT'').
By installing, copying, or otherwise using the SOFTWARE PRODUCT or any UPDATES
(as defined below), you agree to be bound by the terms of this EULA. If you do
not agree to the terms of this EULA, do not install, copy, or otherwise use the
SOFTWARE PRODUCT; you may, however, return it to your place of purchase for a
full refund. In addition, by installing, copying, or otherwise using any updates
or other components of the SOFTWARE PRODUCT that you receive separately as part
of the SOFTWARE PRODUCT (''UPDATES''), you agree to be bound by any additional
license terms that accompany such UPDATES. If you do not agree to the additional
license terms that accompany such UPDATES, you may not install, copy, or
otherwise use such UPDATES.

SOFTWARE PRODUCT LICENSE
The SOFTWARE PRODUCT is protected by copyright laws and international copyright
treaties, as well as other intellectual property laws and treaties. The SOFTWARE
PRODUCT is licensed, not sold. NOTE: The terms of a printed, paper EULA which
may accompany the SOFTWARE PRODUCT supersede the terms of any on-screen EULA
found within the SOFTWARE PRODUCT.

1.      LICENSE TO USE SOFTWARE PRODUCT. 
1.1     General License Grant. Microsoft grants to you as an individual, a personal,
nonexclusive license to make and use copies of the SOFTWARE PRODUCT for the sole
purposes of designing, developing, and testing your software product(s) that are
designed to operate in conjunction with any Microsoft operating system product.
You may install copies of the SOFTWARE PRODUCT on an unlimited number of
computers provided that you are the only individual using the SOFTWARE PRODUCT.
If you are an entity, Microsoft grants you the right to designate one individual
within your organization to have the sole right to use the SOFTWARE PRODUCT in
the manner provided above.
1.2     Documentation. This EULA grants you, as an individual, a personal,
nonexclusive license to make and use an unlimited number of copies of any
documentation, provided that such copies shall be used only for personal
purposes and are not to be republished or distributed (either in hard copy or
electronic form) beyond the user's premises and with the following exception:
you may use documentation identified in the MSDN Library portion of the SOFTWARE
PRODUCT as the file format specification for Microsoft Word, Microsoft Excel,
Microsoft Access, and/or Microsoft PowerPoint ("File Format Documentation")
solely in connection with your development of software product(s) that operate
in conjunction with Windows or Windows NT that are not general purpose word
processing, spreadsheet, or database management software products or an
integrated work or product suite whose components include one or more general
purpose word processing, spreadsheet, or database management software products.
Note: A product that includes limited word processing, spreadsheet, or database
components along with other components that provide significant and primary
value, such as an accounting product with limited spreadsheet capability, is not
considered to be a "general purpose" product.
1.3     Storage/Network Use. You may also store or install a copy of the SOFTWARE
PRODUCT on a storage device, such as a network server, used only to install or
run the SOFTWARE PRODUCT on computers used by a licensed end user in accordance
with Section 1.1.  A single license for the SOFTWARE PRODUCT may not be shared
or used concurrently by other end users.
1.4     Visual Studio-Effect of EULA. This Section 1.4 also applies if the SOFTWARE
PRODUCT is Microsoft Visual Studio, a suite of development tools and other
software programs (each such tool or software program, a "Component").
Components that you receive as part of the SOFTWARE PRODUCT may include a
separate end-user license agreement (each, a "Component EULA"). Except as
provided in Section 6, in the event of inconsistencies between this EULA and any
Component EULA, the terms of this EULA shall control.
1.5     Microsoft Internet Explorer. You may make and use copies of the Microsoft
Internet Explorer for use on all computers for which you have a validly licensed
copy of Microsoft operating system products. 

2.      REDISTRIBUTABLE CODE-ADDITIONAL LICENSE RIGHTS. In addition to the rights
granted in Section 1, certain portions of the SOFTWARE PRODUCT, as described in
this Section 2, are provided to you with additional license rights provided that
you comply with the terms of 
Section 3.1.
2.1     Sample Code. Microsoft grants you the right to use and modify the source
code version of those portions of the SOFTWARE PRODUCT identified as "Samples"
in REDIST.TXT or elsewhere in the SOFTWARE PRODUCT ("Sample Code") for the sole
purposes of designing, developing, and testing your software product(s), and to
reproduce and distribute the Sample Code, along with any modifications thereof,
only in object code form.
2.2     Redistributable Code-Standard. Microsoft grants you a nonexclusive,
royalty-free right to reproduce and distribute the object code form of any
portion of the SOFTWARE PRODUCT listed in REDIST.TXT ("Redistributable Code").
NOTE: certain Redistributable Code may be subject to the restrictions in Section
2.3 if it is also identified as "Limited Use Redistributable Code."
2.3     Redistributable Code-Limited Use. Provided that you ALSO comply with the
terms of Section 3.1.3, Microsoft grants you a nonexclusive, royalty-free right
to reproduce and distribute the object code form of those portions of the
SOFTWARE PRODUCT listed in REDIST.TXT as Limited Use Redistributable Code
("Limited Use Redistributable Code").
2.4     Visual C++ and Visual Studio-Microsoft Foundation Classes (MFC), Template
Libraries (ATL), and C runtimes (CRTs). If this EULA accompanies Visual C++ or
Visual Studio, then in addition to the rights granted in Section 1, Microsoft
grants you the right to use and modify the source code version of those portions
of the SOFTWARE PRODUCT that are identified as MFC, ATL, or CRTs (collectively,
the "VC Redistributables"), for the sole purposes of designing, developing, and
testing your software product(s). Provided you comply with Section 3.1 and you
rename any files created by you that are included in the Licensed Product
(defined below), Microsoft grants you a nonexclusive, royalty-free right to
reproduce and distribute the object code version of the VC Redistributables,
including any modifications you make. For purposes of this section,
"modifications" shall mean enhancements to the functionality of the VC
Redistributables.

3.      DISTRIBUTION REQUIREMENTS; LICENSE RESTRICTIONS. 
3.1     General. The SOFTWARE PRODUCT may contain up to three categories of
redistributable code, any redistribution of which by you requires compliance
with the following terms. 
3.1.1.  Redistributable Code-Standard. If you are authorized and choose to
redistribute Sample Code, Redistributable Code, and Limited Use Redistributable
Code, (collectively, the "Redistributables") as described in Section 2, you
agree to: (a) distribute the Redistributables in object code form and only in
conjunction with and as a part of a software application product developed by
you using the product accompanying this EULA that adds significant and primary
functionality to the SOFTWARE PRODUCT ("Licensed Product"); (b) not use
Microsoft's name, logo, or trademarks to market the Licensed Product; (c)
include a valid copyright notice on the Licensed Product; (d) indemnify, hold
harmless, and defend Microsoft from and against any claims or lawsuits,
including attorney's fees, that arise or result from the use or distribution of
the Licensed Product; (e) include "Copyright  Microsoft Systems Journal"
in all Microsoft Systems Journal (MSJ) code used within your program(s); (f)
otherwise comply with the terms of this EULA; and (g) agree that Microsoft
reserves all rights not expressly granted. You also agree not to permit further
distribution of the Redistributables by your end users except: (1) you may
permit further redistribution of the Redistributables by your distributors to
your end-user customers if your distributors only distribute the
Redistributables in conjunction with, and as part of, the Licensed Product and
you and your distributors comply with all other terms of this EULA; and (2) in
the manner described in Section 3.1.2. 
3.1.2   Redistributable Code-Extended Use. Visual Basic, Visual C++, Visual J++,
and Visual Studio. If this EULA accompanies any of the Microsoft products listed
in the heading of this subsection, and subject to your compliance with Section
3.1.1, you may permit your end users to reproduce and distribute the object code
form of certain portions of the SOFTWARE PRODUCT (as listed in REDIST.TXT as
"Extended Use Redistributable Code") only in conjunction with and part of a
Licensed Product and/or Web page that adds significant and primary functionality
to the Extended Use Redistributable Code. (NOTE: The foregoing license grant
does not apply to files designated as Dbgrid.ocx and Graph32.ocx). You are
authorized to exercise the foregoing rights provided that: 
(a)     you comply with Section 3.1.1, and 
(b)     your end user agrees to: (a) distribute the Extended Use Redistributable
Code in object code only in conjunction with and as a part of a software
application product developed by them that adds significant and primary
functionality to the Extended Use Redistributable Code; (b) not use Microsoft's
name, logo, or trademarks to market the End-User Application; (c) include a
valid copyright notice on the End-User Application; (d) indemnify, hold
harmless, and defend Microsoft from and against any claims or lawsuits,
including attorney's fees, that arise or result from the use or distribution of
the End-User Application; and (e) not permit further distribution of the
Extended Use Redistributable Code by the user of the End-User Application.
3.1.3   Redistributable Code-Limited Use. If you are authorized and choose to
redistribute Limited Use Redistributable Code, in addition to the terms of
Section 3.1.1, you must also comply with the following (as applicable to the
corresponding portions of the SOFTWARE PRODUCT identified in REDIST.TXT as
Limited Use Redistributable Code). 
3.1.3.1 "Jet" Files. If you redistribute the "Jet Files" (as identified in the
SOFTWARE PRODUCT) you agree to comply with the following additional
requirements: (a) your Licensed Product shall not substantially duplicate the
capabilities of Microsoft Access or, in the reasonable opinion of Microsoft,
compete with same; and (b) unless your Licensed Product requires your customers
to license Microsoft Access in order to operate, you shall not reproduce or use
any of the Jet Files for commercial distribution in conjunction with a general
purpose word processing, spreadsheet or database management software product, or
an integrated work or product suite whose components include a general purpose
word processing, spreadsheet, or database management software product except for
the exclusive use of importing data to the various formats supported by
Microsoft Access. Note: A product that includes limited word processing,
spreadsheet or database components along with other components which provide
significant and primary value, such as an accounting product with limited
spreadsheet capability, is not considered to be a "general purpose" product. 
3.1.3.2 Microsoft Data Access Components. If you redistribute the Microsoft Data
Access Component file identified as MDAC_TYP.EXE, you also agree to redistribute
such file in object code only in conjunction with and as a part of a Licensed
Product developed by you with a Microsoft development tool product that adds
significant and primary functionality to MDAC_TYP.EXE.

4.      MICROSOFT WINDOWS NT OPTION PACK COMPONENTS.  Notwithstanding anything to the
contrary contained in this EULA, solely for those portions of the SOFTWARE
PRODUCT identified as the Microsoft Windows NT Option Pack Components, the
following provisions apply.  Note that your use of the Microsoft Windows NT
Option Pack Components is (a) subject to your prior acquisition of a validly
licensed copy of certain Microsoft operating system or server products; and (b)
all capitalized terms in this Section  4 refer to those terms as defined in the
end user-license agreement for the Windows NT Option Pack Component referenced
in the respective paragraphs of this Section (all such terms are noted in brackets):
4.1     IF YOU USE THE SOFTWARE COMPONENTS AS PART OF MICROSOFT WINDOWS NT SERVER
4.0, MICROSOFT WINDOWS NT SERVER ENTERPRISE EDITION 4.0 OR MICROSOFT BACKOFFICE
2.5, THE FOLLOWING TERMS APPLY TO YOU:
NOTE:  IF YOU DO NOT HAVE A VALID LICENSE FOR MICROSOFT WINDOWS NT SERVER 4.0,
MICROSOFT WINDOWS NT SERVER ENTERPRISE EDITION 4.0, OR MICROSOFT BACKOFFICE 2.5,
YOU ARE NOT AUTHORIZED TO INSTALL, COPY OR OTHERWISE USE THE WINDOWS NT SOFTWARE
COMPONENTS.  FOR PURPOSES OF THIS SECTION 4.1, THE "WINDOWS NT SOFTWARE
COMPONENTS" SHALL MEAN THE FOLLOWING SOFTWARE COMPONENTS:  MICROSOFT MESSAGE
QUEUE SERVER, MICROSOFT TRANSACTION SERVER, MICROSOFT INTERNET INFORMATION
SERVER AND THE INTERNET CONNECTION SERVICES FOR MICROSOFT REMOTE ACCESS SERVICE.
 EVEN IF YOU HAVE A RIGHT TO USE THE WINDOWS NT SOFTWARE COMPONENTS, YOU DO NOT
HAVE ANY RIGHT TO INSTALL, COPY OR OTHERWISE USE ANY OF THE OTHER WINDOWS NT
OPTION PACK COMPONENTS, UNLESS OTHERWISE PROVIDED IN A DIFFERENT PARAGRAPH OF
THIS SECTION.
4.1.1   General.  The Windows NT Software Components contain server software and
client software which are deemed part of the [Server Software] and [Client
Software], respectively, of Microsoft Windows NT Server 4.0 (either as a
standalone product or as a component of Microsoft BackOffice) or Microsoft
Windows NT Server, Enterprise Edition 4.0, as applicable.  If you have a valid
license for Microsoft Windows NT Server 4.0, Microsoft Windows NT Server
Enterprise Edition 4.0 or Microsoft BackOffice 2.5 (each referred to
individually as a ["SOFTWARE PRODUCT"]), you are authorized to use the Windows
NT Software Components under the terms and conditions of the EULA applicable to
such product, except as set forth herein.  
4.1.2   For Microsoft Windows NT Server-Client Access.  In addition to the [Client
Access] requirements currently set forth in the applicable EULA, you need a
separate [Client Access License] for Windows NT Server in order to access or
otherwise utilize the following Windows NT Server basic network/application
services or [Server Software] components:  Microsoft Message Queue Server
(sending or receiving messages from Microsoft Message Queue Server), Microsoft
Transaction Server (invoking component-based applications managed by Microsoft
Transaction Server), and Remote Access Service (accessing the server from a
remote location through a communications link).  Note:  Remote Access Service
includes the use of Internet Connection Services, including Internet
Authentication Services (validation or transference of a remote access request)
or Connection Point Services (remotely configuring the Microsoft Connection
Manager Client with new phone numbers or other data).  Performance or Benchmark
Testing.  You may not disclose the results of any benchmark test of either the
[Server Software] or [Client Software] for Microsoft Message Queue Server,
Microsoft Transaction Server or Microsoft Internet Information Server to any
third party without Microsoft's prior written approval.  Installation on a
Single [Server].  The [Server Software] components that make up the applicable
[SOFTWARE PRODUCT] may only be installed together for use on one [Server] and
may not be separated, unless otherwise provided herein.  Note on Microsoft Site
Server Express.  You may freely copy and distribute Microsoft Site Server
Express for your use on any computer within your organization.
4.1.3   For Microsoft Internet Information Server-Use. Notwithstanding anything to
the contrary contained in the applicable EULA, you do not need a separate
[Client Access License] to access or otherwise utilize the services of Microsoft
Internet Information Server, except to the extent that a [Server] or [Server
Software] component which requires a [Client Access License] is accessed or
utilized by Microsoft Internet Information Server.
4.1.4   Additional Rights and Restrictions. You also have the right to make
additional copies of the Windows NT Software Components equal to the number of
validly licensed copies of each [SOFTWARE PRODUCT] which you have, and you may
use each copy in the manner specified above.  If you do not have a valid license
for Microsoft Windows NT Server 4.0, Microsoft Windows NT Server Enterprise
Edition 4.0 or Microsoft BackOffice 2.5, you have no rights under the foregoing
section.  
4.2     IF YOU USE THE SOFTWARE COMPONENTS AS PART OF MICROSOFT WINDOWS NT
WORKSTATION 4.0, THE FOLLOWING TERMS APPLY TO YOU:
NOTE:  IF YOU DO NOT HAVE A VALID LICENSE FOR MICROSOFT WINDOWS NT WORKSTATION
4.0, YOU ARE NOT AUTHORIZED TO INSTALL, COPY OR OTHERWISE USE THE WINDOWS NT
WORKSTATION SOFTWARE COMPONENTS.  FOR PURPOSES OF THIS SECTION 4.2, THE "WINDOWS
NT WORKSTATION SOFTWARE 
COMPONENTS" SHALL MEAN THE FOLLOWING SOFTWARE COMPONENTS:  MICROSOFT TRANSACTION
SERVER AND MICROSOFT PERSONAL WEB SERVER.  EVEN IF YOU HAVE A RIGHT TO USE THE
WINDOWS NT WORKSTATION SOFTWARE COMPONENTS, YOU DO NOT HAVE ANY RIGHT TO
INSTALL, COPY OR USE ANY OF THE OTHER SOFTWARE COMPONENTS, UNLESS OTHERWISE
PROVIDED IN A DIFFERENT PARAGRAPH OF THIS SECTION.
4.2.1   General. The Windows NT Workstation Software Components are deemed part of
Microsoft Windows NT Workstation 4.0 (the ["SOFTWARE PRODUCT"]), and are
therefore subject to the terms and conditions of its EULA, except as otherwise
provided herein.  Use Limitation. At any point in time, only a maximum of two
(2) computers (instead of ten (10) are permitted to use the services of the
Microsoft Transaction Server component.  The two (2) computer maximum includes
any indirect uses made through software or hardware which pools or aggregates
uses.  Performance or Benchmark Testing. You may not disclose the results of any
benchmark test of either of the Windows NT Workstation Software Components to
any third party without Microsoft's prior written approval.
4.2.2   Additional Rights and Restrictions.  You also have the right to make
additional copies of the Windows NT Workstation Software Components equal to the
number of validly licensed copies of Microsoft Windows NT Workstation 4.0 which
you have, and you may use each copy in the manner specified above.  If you do
not have a valid license for Microsoft Windows NT Workstation 4.0, you have no
rights under the foregoing section.
4.3     IF YOU USE THE SOFTWARE COMPONENTS AS PART OF MICROSOFT BACKOFFICE SMALL
BUSINESS SERVER 4.0, THE FOLLOWING TERMS APPLY TO YOU:
NOTE:  IF YOU DO NOT HAVE A VALID LICENSE FOR MICROSOFT BACKOFFICE SMALL
BUSINESS SERVER 4.0, YOU ARE NOT AUTHORIZED TO INSTALL, COPY OR OTHERWISE USE
THE WINDOWS NT SOFTWARE COMPONENTS (AS DEFINED PREVIOUSLY IN SECTION 4.1).  EVEN
IF YOU HAVE THE RIGHT TO USE THE WINDOWS NT SOFTWARE COMPONENTS, YOU DO NOT HAVE
ANY RIGHT TO INSTALL, COPY OR OTHERWISE USE ANY OF THE OTHER SOFTWARE
COMPONENTS, UNLESS OTHERWISE PROVIDED IN A DIFFERENT PARAGRAPH OF THIS SECTION.
4.3.1   General. The Windows NT Software Components contain server software and
client software which is deemed part of the [Server Software] and [Client
Software], respectively, of Microsoft BackOffice Small Business Server 4.0, and
is therefore subject to the terms and conditions of its EULA, except as
otherwise provided herein.  Note on Microsoft Site Server Express. You may
freely copy and distribute Microsoft Site Server Express for your use on any
computer within your organization.
4.3.2   Additional Rights and Restrictions. You also have the right to make
additional copies of the Windows NT Software Components equal to the number of
validly licensed copies of Microsoft BackOffice Small Business Server 4.0 which
you have, and you may use each copy in the manner specified above.  If you do
not have a valid license for Microsoft BackOffice Small Business Server 4.0, you
have no rights under the foregoing section.
4.4     IF YOU USE THE SOFTWARE COMPONENTS AS PART OF MICROSOFT WINDOWS 95, THE
FOLLOWING TERMS APPLY TO YOU:
NOTE:  IF YOU DO NOT HAVE A VALID LICENSE FOR MICROSOFT WINDOWS 95, YOU ARE NOT
AUTHORIZED TO INSTALL, COPY OR OTHERWISE USE THE WINDOWS 95 SOFTWARE COMPONENTS.
 FOR PURPOSES OF THIS SECTION 4.4, THE "WINDOWS 95 SOFTWARE COMPONENTS" SHALL
MEAN THE FOLLOWING SOFTWARE COMPONENTS:  MICROSOFT PERSONAL WEB SERVER AND
MICROSOFT TRANSACTION SERVER FOR WINDOWS 95.  EVEN IF YOU HAVE A RIGHT TO USE
THE WINDOWS 95 SOFTWARE COMPONENTS, YOU DO NOT HAVE ANY RIGHT TO INSTALL, COPY
OR USE ANY OF THE OTHER SOFTWARE COMPONENTS, UNLESS OTHERWISE PROVIDED IN A
DIFFERENT PARAGRAPH OF THIS SECTION.
4.4.1   General. The Windows 95 Software Components are deemed part of Microsoft
Windows 95 (the ["SOFTWARE PRODUCT"]), and are therefore subject to the terms
and conditions of its EULA, except as otherwise provided herein.
4.4.2   Use Limitation. At any point in time, a maximum of ten (10) computers are
permitted to use the services of the Microsoft Personal Web Server component. 
The ten (10) computer maximum includes any indirect uses made through software
or hardware which pools or aggregates uses. The Microsoft Transaction Server for
Windows 95 component may not be used as a network server; that is, no computers
or workstations may access or utilize any network services of that component.
Performance or Benchmark Testing. You may not disclose the results of any
benchmark test of either of the Windows 95 Software Components to any third
party without Microsoft's prior written approval.
4.4.3   Additional Rights and Restrictions. You also have the right to make
additional copies of the Windows 95 Software Components equal to the number of
validly licensed copies of Microsoft Windows 95 which you have, and you may use
each copy in the manner specified above.  If you do not have a valid license for
Microsoft Windows 95, you have no rights under the foregoing section.

5.      DESCRIPTION OF OTHER RIGHTS AND LIMITATIONS
5.1     Not For Resale Software. If the SOFTWARE PRODUCT is labeled "Not For Resale"
or "NFR," then you may not resell, or otherwise transfer for value, the SOFTWARE
PRODUCT.
5.2     Limitations on Reverse Engineering, Decompilation, and Disassembly. You may
not reverse engineer, decompile, or disassemble the SOFTWARE PRODUCT, except and
only to the extent that such activity is expressly permitted by applicable law
notwithstanding this limitation.
5.3     Rental. You may not rent, lease, or lend the SOFTWARE PRODUCT.
5.4     Trademarks. This EULA does not grant you any rights in connection with any
trademarks or service marks of Microsoft.
5.5     Support Services. Microsoft may provide you with support services related to
the SOFTWARE PRODUCT ("Support Services"). Use of Support Services is governed
by the Microsoft policies and programs described in the user manual, in "online"
documentation 
and/or in other Microsoft-provided materials. Any supplemental software code
provided to you as part of the Support Services shall be considered part of the
SOFTWARE PRODUCT and subject to the terms and conditions of this EULA. With
respect to technical information you provide to Microsoft as part of the Support
Services, Microsoft may use such information for its business purposes,
including for product support and development. Microsoft will not utilize such
technical information in a form that personally identifies you.
5.6     Software Transfer. The initial user of the SOFTWARE PRODUCT may make a
one-time permanent transfer of this EULA and SOFTWARE PRODUCT only directly to
an end user. This transfer must include all of the SOFTWARE PRODUCT (including
all component parts, the media and printed materials, any upgrades, this EULA,
and, if applicable, the Certificate of Authenticity). Such transfer may not be
by way of consignment or any other indirect transfer. The transferee of such
one-time transfer must agree to comply with the terms of this EULA, including
the obligation not to further transfer this EULA and SOFTWARE PRODUCT.
5.7     Separation of Components. The SOFTWARE PRODUCT is licensed as a single
product. Its component parts may not be separated for use by more than one user. 
5.8     Termination. Without prejudice to any other rights, Microsoft may terminate
this EULA if you fail to comply with the terms and conditions of this EULA. In
such event, you must destroy all copies of the SOFTWARE PRODUCT and all of its
component parts.

6.      PRERELEASE CODE. Portions of the SOFTWARE PRODUCT may be identified as
prerelease code ("Prerelease Code"). Such Prerelease Code is not at the level of
performance and compatibility of the final, generally available product
offering. The Prerelease Code may not operate correctly and may be substantially
modified prior to first commercial shipment. Microsoft is not obligated to make
this or any later version of the Prerelease Code commercially available. The
grant of license to use Prerelease Code expires upon availability of a
commercial release of the Prerelease Code from Microsoft. NOTE: In the event
that Prerelease Code contains a separate end-user license agreement, the terms
and conditions of such end-user license agreement shall govern your use of the
corresponding Prerelease Code.

7.      UPGRADES. If the SOFTWARE PRODUCT is labeled as an upgrade, you must be
properly licensed to use a product identified by Microsoft as being eligible for
the upgrade in order to use the SOFTWARE PRODUCT. A SOFTWARE PRODUCT labeled as
an upgrade replaces and/or supplements the product that formed the basis for
your eligibility for the upgrade. You may use the resulting upgraded product
only in accordance with the terms of this EULA. If the SOFTWARE PRODUCT is an
upgrade of a component of a package of software programs that you licensed as a
single product, the SOFTWARE PRODUCT may be used and transferred only as part of
that single product package and may not be separated for use on more than one
computer.

8.      COPYRIGHT. All title and intellectual property rights in and to the SOFTWARE
PRODUCT (including but not limited to any images, photographs, animations,
video, audio, music, text, and "applets" incorporated into the SOFTWARE
PRODUCT), the accompanying printed materials, and any copies of the SOFTWARE
PRODUCT are owned by Microsoft or its suppliers. All title and intellectual
property rights in and to the content which may be accessed through use of the
SOFTWARE PRODUCT is the property of the respective content owner and may be
protected by applicable copyright or other intellectual property laws and
treaties. This EULA grants you no rights to use such content. All rights not
expressly granted are reserved by Microsoft.

9.      U.S. GOVERNMENT RESTRICTED RIGHTS. The SOFTWARE PRODUCT and documentation are
provided with RESTRICTED RIGHTS. Use, duplication, or disclosure by the
Government is subject to restrictions as set forth in subparagraph (c)(1)(ii) of
the Rights in Technical Data and Computer Software clause at DFARS 252.227-7013
or subparagraphs (c)(1) and (2) of the Commercial Computer Software-Restricted
Rights at 58 CFR 52.227-19, as applicable. Manufacturer is Microsoft
Corporation/One Microsoft Way/Redmond, WA 98052-6399.

10.     EXPORT RESTRICTIONS. You agree that you will not export or re-export the
SOFTWARE PRODUCT, any part thereof, or any process or service that is the direct
product of the SOFTWARE PRODUCT (the foregoing collectively referred to as the
"Restricted Components"), to any country, person, entity or end user subject to
U.S. export restrictions. You specifically agree not to export or re-export any
of the Restricted Components (i) to any country to which the U.S. has embargoed
or restricted the export of goods or services, which currently include, but are
not necessarily limited to Cuba, Iran, Iraq, Libya, North Korea, Sudan and
Syria, or to any national of any such country, wherever located, who intends to
transmit or transport the Restricted Components back to such country; (ii) to
any end-user who you know or have reason to know will utilize the Restricted
Components in the design, development or production of nuclear, chemical or
biological weapons; or (iii) to any end-user who has been prohibited from
participating in U.S. export transactions by any federal agency of the U.S.
government. You warrant and represent that neither the BXA nor any other U.S.
federal agency has suspended, revoked, or denied your export privileges.

11.     NOTE ON JAVA SUPPORT. THE SOFTWARE PRODUCT CONTAINS SUPPORT FOR PROGRAMS
WRITTEN IN JAVA. JAVA TECHNOLOGY IS NOT FAULT TOLERANT AND IS NOT DESIGNED,
MANUFACTURED, OR INTENDED FOR USE OR RESALE AS ONLINE CONTROL EQUIPMENT IN
HAZARDOUS ENVIRONMENTS REQUIRING FAIL-SAFE PERFORMANCE, SUCH AS IN THE OPERATION
OF NUCLEAR FACILITIES, AIRCRAFT NAVIGATION OR COMMUNICATIONS SYSTEMS, AIR
TRAFFIC CONTROL, DIRECT LIFE SUPPORT MACHINES, OR WEAPONS SYSTEMS, IN WHICH THE
FAILURE OF JAVA TECHNOLOGY COULD LEAD DIRECTLY TO DEATH, PERSONAL INJURY, OR
SEVERE PHYSICAL OR ENVIRONMENTAL DAMAGE.
MISCELLANEOUS
If you acquired this product in the United States, this EULA is governed by the
laws of the State of Washington. 
If you acquired this product in Canada, this EULA is governed by the laws of the
Province of Ontario, Canada. Each of the parties hereto irrevocably attorns to
the jurisdiction of the courts of the Province of Ontario and further agrees to
commence any litigation which may arise hereunder in the courts located in the
Judicial District of York, Province of Ontario. 
If this product was acquired outside the United States, then local law may apply.
Should you have any questions concerning this EULA, or if you desire to contact
Microsoft for any reason, please contact Microsoft, or write: Microsoft Sales
Information Center/One Microsoft Way/Redmond, WA 98052-6399. 

LIMITED WARRANTY
LIMITED WARRANTY. Except with respect to the REDISTRIBUTABLES, which are
provided "as is," without warranty of any kind,  Microsoft warrants that (a) the
SOFTWARE PRODUCT will perform substantially in accordance with the accompanying
written materials for a period of ninety (90) days from the date of receipt, and
(b) any Support Services provided by Microsoft shall be substantially as
described in applicable written materials provided to you by Microsoft, and
Microsoft support engineers will make commercially reasonable efforts to solve
any problem. To the extent allowed by applicable law, implied warranties on the
SOFTWARE PRODUCT, if any, are limited to ninety (90) days. Some
states/jurisdictions do not allow limitations on duration of an implied
warranty, so the above limitation may not apply to you.

CUSTOMER REMEDIES. Microsoft's and its suppliers' entire liability and your
exclusive remedy shall be, at Microsoft's option, either (a) return of the price
paid, if any, or (b) repair or replacement of the SOFTWARE PRODUCT that does not
meet Microsoft's Limited Warranty and that is returned to Microsoft with a copy
of your receipt. This Limited Warranty is void if failure of the SOFTWARE
PRODUCT has resulted from accident, abuse, or misapplication. Any replacement
SOFTWARE PRODUCT will be warranted for the remainder of the original warranty
period or thirty (30) days, whichever is longer. Outside the United States,
neither these remedies nor any product support services offered by Microsoft are
available without proof of purchase from an authorized international source.

NO OTHER WARRANTIES. TO THE MAXIMUM EXTENT PERMITTED BY APPLICABLE LAW,
MICROSOFT AND ITS SUPPLIERS DISCLAIM ALL OTHER WARRANTIES AND CONDITIONS, EITHER
EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, IMPLIED WARRANTIES OR
CONDITIONS OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, TITLE AND
NON-INFRINGEMENT, WITH REGARD TO THE SOFTWARE PRODUCT, AND THE PROVISION OF OR
FAILURE TO PROVIDE SUPPORT SERVICES. THIS LIMITED WARRANTY GIVES YOU SPECIFIC
LEGAL RIGHTS. YOU MAY HAVE OTHERS, WHICH VARY FROM STATE/JURISDICTION TO
STATE/JURISDICTION.

LIMITATION OF LIABILITY. TO THE MAXIMUM EXTENT PERMITTED BY APPLICABLE LAW, IN
NO EVENT SHALL MICROSOFT OR ITS SUPPLIERS BE LIABLE FOR ANY SPECIAL, INCIDENTAL,
INDIRECT, OR CONSEQUENTIAL DAMAGES WHATSOEVER (INCLUDING, WITHOUT LIMITATION,
DAMAGES FOR LOSS OF BUSINESS PROFITS, BUSINESS INTERRUPTION, LOSS OF BUSINESS
INFORMATION, OR ANY OTHER PECUNIARY LOSS) ARISING OUT OF THE USE OF OR INABILITY
TO USE THE SOFTWARE PRODUCT OR THE FAILURE TO PROVIDE SUPPORT SERVICES, EVEN IF
MICROSOFT HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES. IN ANY CASE,
MICROSOFT'S ENTIRE LIABILITY UNDER ANY PROVISION OF THIS EULA SHALL BE LIMITED
TO THE GREATER OF THE AMOUNT ACTUALLY PAID BY YOU FOR THE SOFTWARE PRODUCT OR
U.S.$5.00; PROVIDED, HOWEVER, IF YOU HAVE ENTERED INTO A MICROSOFT SUPPORT
SERVICES AGREEMENT, MICROSOFT'S ENTIRE LIABILITY REGARDING SUPPORT SERVICES
SHALL BE GOVERNED BY THE TERMS OF THAT AGREEMENT. BECAUSE SOME
STATES/JURISDICTIONS DO NOT ALLOW THE EXCLUSION OR LIMITATION OF LIABILITY, THE
ABOVE LIMITATION MAY NOT APPLY TO YOU.


Si vous avez acquis votre produit Microsoft au CANADA, la garantie
limitýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýe
suivante vous concerne :

GARANTIE LIMITEE
GARANTIE LIMITýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýE - Sauf pour celles du
REDISTRIBUTABLES, qui sont
fournies
"comme telles," sans acune garantie quelle qu'elle soit,  Microsoft garantit que
(a) la performance du LOGICIEL sera substantiellement en
conformitýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýý avec la
documentation qui accompagne le LOGICIEL, pour une
pýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýriode de
quatre-vingt-dix
(90) jours ýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýý compter de la date de
rýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýception; et
(b) tout support
technique
fourni par Microsoft sera substantiellement en
conformitýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýý avec toute
documentation affýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýrente fournie par Microsoft et
que les membres
du support
technique de Microsoft feront des efforts raisonnables pour
rýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýsoudre toute
difficultýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýý technique
dýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýcoulant de l'utilisation du
LOGICIEL. Certaines
juridictions ne permettent pas de limiter dans le temps l'application de la
prýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýsente garantie. Aussi, la limite
stipulýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýe
ci-haut pourrait ne pas
s'appliquer
dans votre cas. Dans la mesure permise par la loi, toute garantie implicite
portant sur le LOGICIEL, le cas
ýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýchýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýant, est
limitýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýe
ýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýý une pýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýriode de
quatre-vingt-dix (90) jours.

RECOURS DU CLIENT - La seule obligation de Microsoft et de ses fournisseurs et
votre recours exclusif seront, au choix de Microsoft, soit (a) le remboursement
du prix payýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýý, si applicable, ou (b) la
rýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýparation
ou le
remplacement du
LOGICIEL
qui n'est pas conforme ýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýý la Garantie
Limitýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýe de
Microsoft et qui est
retournýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýý ýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýý
Microsoft avec une copie de votre reýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýu. Cette
Garantie
Limitýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýe est
nulle si le
dýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýfaut du LOGICIEL est
causýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýý par un accident, un
traitement abusif
ou une
mauvaise application. Tout LOGICIEL de remplacement sera garanti pour le reste
de la pýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýriode de garantie initiale ou pour trente
(30) jours,
selon la plus
longue de ces pýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýriodes. A
l'extýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýrieur des
Etats-Unis, aucun de ces
recours non
plus que le support technique offert par Microsoft ne sont disponibles sans une
preuve d'achat provenant d'une source authorisýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýe. 

AUCUNE AUTRE GARANTIE - DANS LA MESURE PREVUE PAR LA LOI, MICROSOFT ET SES
FOURNISSEURS EXCLUENT TOUTE AUTRE GARANTIE OU CONDITION, EXPRESSE OU IMPLICITE,
Y COMPRIS MAIS NE SE LIMITANT PAS AUX GARANTIES OU CONDITIONS IMPLICITES DU
CARACTERE ADEQUAT POUR LA COMMERCIALISATION OU UN USAGE PARTICULIER EN CE QUI
CONCERNE LE LOGICIEL OU CONCERNANT LE TITRE, L'ABSENCE DE
CONTREFAýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýON DUDIT
LOGICIEL, ET TOUTE DOCUMENTATION ECRITE QUI L'ACCOMPAGNE, AINSI QUE POUR TOUTE
DISPOSITION CONCERNANT LE SUPORT TECHNIQUE OU LA
FAýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýON DONT
CELUI-CI A ETE
RENDU. CETTE GARANTIE LIMITEE VOUS ACCORDE DES DROITS JURIDIQUES SPECIFIQUES. 

PAS DE RESPONSABILITE POUR LES DOMMAGES INDIRECTS - MICROSOFT OU SES
FOURNISSEURS NE SERONT PAS RESPONSABLES, EN AUCUNE CIRCONSTANCE, POUR TOUT
DOMMAGE SPECIAL, INCIDENT, INDIRECT, OU CONSEQUENT QUEL QU'IL SOIT (Y COMPRIS,
SANS LIMITATION, LES DOMMAGES ENTRAINES PAR LA PERTE DE BENEFICES,
L'INTERRUPTION DES ACTIVITES, LA PERTE D'INFORMATION OU TOUTE AUTRE PERTE
PECUNIAIRE) DECOULANT DE OU RELIE A LA LICENCE D'ACCES DU CLIENTET CE, MEME SI
MICROSOFT A ETE AVISEE DE LA POSSIBILITE DE TELS DOMMAGES. LA RESPONSABILITE DE
MICROSOFT EN VERTU DE TOUTE DISPOSITION DE CETTE CONVENTION NE POURRA EN AUCUN
TEMPS EXCEDER LE PLUS ELEVE ENTRE I) LE MONTANT EFFECTIVEMENT PAYE PAR VOUS POUR
LA LICENCE D'ACCES DU CLIENT OU II) U.S.$5.00. ADVENANT QUE VOUS AYEZ CONTRACTE
PAR ENTENTE DISTINCTE AVEC MICROSOFT POUR UN SUPPORT TECHNIQUE ETENDU, VOUS
SEREZ LIE PAR LES TERMES D' UNE TELLE ENTENTE.

La prýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýsente Convention est
rýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýgie par les lois en
vigeur dans ela
province
d'Ontario, Canada. Chacune des parties ýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýý la
prýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýsente reconnaýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýt
irrýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýývocablement
la compýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýtence des tribunaux de la province
d'Ontario et consent
ýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýý
instituer tout
litige qui pourrait dýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýcouler de la
prýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýsente
auprýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýs des
tribunaux
situýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýs dans le
district judiciaire de York, province d'Ontario.

Au cas oýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýý vous auriez des questions concernant
cette licence ou
que vous
dýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýsiriez vous mettre en rapport avec Microsoft
pour quelque
raison que ce
soit,
veuillez contacter la succursale Microsoft desservant votre pays, dont l'adresse
est fournie dans ce produit, ou ýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýcrire
ýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýýý:
Microsoft Sales
Information Center, 
One Microsoft Way, Redmond, Washington 98052-6399.
%% The following software may be included in this product:  msvcr90.dll, msvcp90.dll 
From Microsoft C/C++ NET. Use of any of this software is governed by the terms of the license below: 

The following list is a list of files available with Microsoft Visual Studio
2008 for redistribution under the Visual Studio 2008 license.  If the Microsoft
software you have licensed is not Visual Studio 2008, only the files that are
installed by the Microsoft software may be redistributed under such license.

_____________________________________________________________

Visual C++ Runtime files

Subject to the license terms for the software, you may redistribute the .EXE
files (unmodified) listed below. 
These files can be run as prerequisites during installation.

vcredist_x86.exe

vcredist_x64.exe 

vcredist_IA64.exe

 


Subject to the license terms for the software, you may redistribute MSM files
listed below unmodified as a part of your installation package:
 
Microsoft_VC90_ATL_x86.msm

policy_9_0_Microsoft_VC90_ATL_x86.msm

Microsoft_VC90_ATL_x86_ia64.msm

policy_9_0_Microsoft_VC90_ATL_x86_ia64.msm

Microsoft_VC90_ATL_x86_x64.msm

policy_9_0_Microsoft_VC90_ATL_x86_x64.msm

Microsoft_VC90_CRT_x86.msm

policy_9_0_Microsoft_VC90_CRT_x86.msm

Microsoft_VC90_CRT_x86_ia64.msm
    
policy_9_0_Microsoft_VC90_CRT_x86_ia64.msm
   
Microsoft_VC90_CRT_x86_x64.msm
    
policy_9_0_Microsoft_VC90_CRT_x86_x64.msm
   
Microsoft_VC90_MFC_x86.msm
    
policy_9_0_Microsoft_VC90_MFC_x86.msm
    
Microsoft_VC90_MFC_x86_ia64.msm
   
policy_9_0_Microsoft_VC90_MFC_x86_ia64.msm

Microsoft_VC90_MFC_x86_x64.msm
   
policy_9_0_Microsoft_VC90_MFC_x86_x64.msm
  
Microsoft_VC90_MFCLOC_x86.msm
  
policy_9_0_Microsoft_VC90_MFCLOC_x86.msm
   
Microsoft_VC90_MFCLOC_x86_ia64.msm
 
policy_9_0_Microsoft_VC90_MFCLOC_x86_ia64.msm
 
Microsoft_VC90_MFCLOC_x86_x64.msm
 
policy_9_0_Microsoft_VC90_MFCLOC_x86_x64.msm

Microsoft_VC90_OpenMP_x86.msm
 
policy_9_0_Microsoft_VC90_OpenMP_x86.msm
      
Microsoft_VC90_OpenMP_x86_ia64.msm
        
policy_9_0_Microsoft_VC90_OpenMP_x86_ia64.msm
    
Microsoft_VC90_OpenMP_x86_x64.msm
    
policy_9_0_Microsoft_VC90_OpenMP_x86_x64.msm
    

For your convenience, we have provided the following folders for use when
redistributing VC++ runtime files. Subject to the license terms for the
software, you may redistribute the folder (unmodified) in the application local
folder as a sub-folder with no change to the folder name. You may also
redistribute all the files (*.dll and *.manifest) within a folder, listed below
the folder for your convenience, as an entire set. 

\VC\redist\x86\Microsoft.VC90.ATL\
 atl90.dll
 Microsoft.VC90.ATL.manifest

\VC\redist\ia64\Microsoft.VC90.ATL\
 atl90.dll
 Microsoft.VC90.ATL.manifest

\VC\redist\amd64\Microsoft.VC90.ATL\
 atl90.dll
 Microsoft.VC90.ATL.manifest

\VC\redist\x86\Microsoft.VC90.CRT\
 msvcm90.dll
 msvcp90.dll
 msvcr90.dll
 Microsoft.VC90.CRT.manifest

\VC\redist\ia64\Microsoft.VC90.CRT\
 msvcm90.dll
 msvcp90.dll
 msvcr90.dll
 Microsoft.VC90.CRT.manifest

\VC\redist\amd64\Microsoft.VC90.CRT\
 msvcm90.dll
 msvcp90.dll
 msvcr90.dll
 Microsoft.VC90.CRT.manifest

\VC\redist\x86\Microsoft.VC90.MFC\
 mfc90.dll
 mfc90u.dll
 mfcm90.dll
 mfcm90u.dll
 Microsoft.VC90.MFC.manifest

\VC\redist\ia64\Microsoft.VC90.MFC\
 mfc90.dll
 mfc90u.dll
 mfcm90.dll
 mfcm90u.dll
 Microsoft.VC90.MFC.manifest

\VC\redist\amd64\Microsoft.VC90.MFC\
 mfc90.dll
 mfc90u.dll
 mfcm90.dll
 mfcm90u.dll
 Microsoft.VC90.MFC.manifest

\VC\redist\x86\Microsoft.VC90.MFCLOC\
 mfc90chs.dll
 mfc90cht.dll
 mfc90enu.dll
 mfc90esp.dll
 mfc90deu.dll
 mfc90fra.dll
 mfc90ita.dll
 mfc90jpn.dll
 mfc90kor.dll
 Microsoft.VC90.MFCLOC.manifest

\VC\redist\ia64\Microsoft.VC90.MFCLOC\
 mfc90chs.dll
 mfc90cht.dll
 mfc90enu.dll
 mfc90esp.dll
 mfc90deu.dll
 mfc90fra.dll
 mfc90ita.dll
 mfc90jpn.dll
 mfc90kor.dll
 Microsoft.VC90.MFCLOC.manifest

\VC\redist\amd64\Microsoft.VC90.MFCLOC\
 mfc90chs.dll
 mfc90cht.dll
 mfc90enu.dll
 mfc90esp.dll
 mfc90deu.dll
 mfc90fra.dll
 mfc90ita.dll
 mfc90jpn.dll
 mfc90kor.dll
 Microsoft.VC90.MFCLOC.manifest

\VC\redist\x86\Microsoft.VC90.OpenMP\
 vcomp90.dll
 Microsoft.VC90.OpenMP.manifest

\VC\redist\amd64\Microsoft.VC90.OpenMP\
 vcomp90.dll
 Microsoft.VC90.OpenMP.manifest

\VC\redist\ia64\Microsoft.VC90.OpenMP\
 vcomp90.dll
 Microsoft.VC90.OpenMP.manifest

Subject to the license terms for the software, the following files can be
redistributed with your application in your application local folder or by
deploying into the Global Assembly Cache (GAC):

mfcmifc80.dll

Subject to the license terms of the software, one or more of the *.*.pdb files
listed below may be redistributed for the sole purpose of debugging your
applications. 

atl90.i386.pdb      
atl90.AMD64.pdb      
atl90.IA64.pdb      
      
msvcm90.i386.pdb      
msvcm90.AMD64.pdb      
msvcm90.IA64.pdb      
      
msvcp90.i386.pdb      
msvcp90.AMD64.pdb      
msvcp90.IA64.pdb      
      
msvcr90.i386.pdb      
msvcr90.AMD64.pdb      
msvcr90.IA64.pdb      
      
mfc90.i386.pdb      
mfc90.AMD64.pdb      
mfc90.IA64.pdb      
      
mfc90u.i386.pdb      
mfc90u.AMD64.pdb      
mfc90u.IA64.pdb      
      
mfcm90.i386.pdb      
mfcm90.AMD64.pdb      
mfcm90.IA64.pdb      
      
mfcm90u.i386.pdb      
mfcm90u.AMD64.pdb      
mfcm90u.IA64.pdb

vcomp90.i386.pdb
vcomp90.AMD64.pdb
vcomp90.IA64.pdb
      
_____________________________________________________________

Smart Device Files

Subject to the license terms for the software, the following files may be
distributed, however, these files are intended only for use with Windows Mobile
and Windows CE based devices and may only be distributed with Smart device
applications:  

Supported processor types are: armv4, armv4i, mipsii, mipsii_fp, mipsiv,
mipsiv_fp, sh4, x86

Folder path for the file location is: \VC\ce\dll\[processor-type-name]

msvcr90.dll
atl90.dll
MFC90CHS.DLL
MFC90CHT.DLL
MFC90DEU.DLL
MFC90ENU.DLL
MFC90ESP.DLL
MFC90FRA.DLL
MFC90ITA.DLL
MFC90JPN.DLL
MFC90KOR.DLL
MFC90U.DLL

Following pdb files may be distributed for debugging purposes only

Folder path for the file location is: \VC\ce\dll\[processor-type-name]

msvcr90.pdb
atl90.pdb
mfc90u.pdb

 

_____________________________________________________________

.NET Framework 

Subject to the license terms for the software, the following .EXE files may be
distributed unmodified:

dotNetFx35setup.exe

For your convenience, we have provided the following folders for use when
redistributing .NET Framework runtime files. Subject to the license terms for
the software, you may redistribute the folder (unmodified) in the application
local folder as a sub-folder with no change to the folder name. You may also
redistribute all the files within a folder, listed below the folder for your
convenience, as an entire set.

wcu\dotNetFramework 
 dotNetFx35setup.exe

wcu\dotNetFramework\dotNetFX20\ 
 ASPNET.msp
 ASPNET_64.msp
 clr.msp
 clr_64.msp
 crt.msp
 crt_64.msp
 dw.msp
 dw_64.msp
 Netfx20a_x64.msi
 Netfx20a_x86.msi
 NetFX_CA.msp
 NetFX_Core.msp
 NetFX_Core_64.msp
 NetFX_Other.msp
 NetFX_Other_64.msp
 prexp.msp
 winforms.msp
 winforms_64.msp

wcu\dotNetFramework\dotNetFX30\ 
 Netfx30a_x64.msi
 Netfx30a_x86.msi
 RGB9RAST_x64.msi
 RGB9RAST_x86.msi
 WCF.msp
 WCF_64.msp
 WCS.msp
 WCS_64.msp
 WF.msp
 WF_32.msp
 WF_64.msp
 WIC_x64_enu.exe
 WIC_x86_enu.exe
 WPF1.msp
 WPF1_64.msp
 WPF2.msp
 WPF2_32.msp
 WPF2_64.msp
 WPF_Other.msp
 WPF_Other_32.msp
 WPF_Other_64.msp
 XPS.msp
 XPSEPSC-amd64-en-US.exe
 XPSEPSC-x86-en-US.exe

wcu\dotNetFramework\dotNetFX30\x64\ 
 msxml6.msi

wcu\dotNetFramework\dotNetFX30\x86\ 
 msxml6.msi

wcu\dotNetFramework\dotNetFX35\x64\ 
 netfx35_x64.exe

wcu\dotNetFramework\dotNetFX35\x86\ 
 netfx35_x86.exe

wcu\dotNetFramework\dotNetMSP\x64\ 
 NetFX2.0-KB110806-v6000-x64.msu

wcu\dotNetFramework\dotNetMSP\x64\ 
 NetFX3.0-KB929300-v6000-x64.msu

wcu\dotNetFramework\dotNetMSP\x86\ 
 NetFX2.0-KB110806-v6000-x86.msu

wcu\dotNetFramework\dotNetMSP\x86\ 
 NetFX3.0-KB929300-v6000-x86.msu

dotnetfx3.exe

dotnetfx.exe

enabledotnet3.exe




_____________________________________________________________


.NET Compact Framework Files

For your convenience, we have provided the following installation package
(*.MSI) for use when redistributing the .NET Compact Framework dlls. Subject to
the license terms for the software, you may redistribute the MSI (unmodified) or
one or more of the separate files listed below:

.NET Compact Framework V2:

NETCFSetupv2.msi
 NETCFv2.ppc.armv4.cab
 NETCFv2.wm.armv4i.cab
 NETCFv2.wce5.sh4.cab
 NETCFv2.wce5.mipsii.cab
 NETCFv2.wce5.mipsiv.cab
 NETCFv2.wce5.armv4i.cab
 NETCFv2.wce5.x86.cab
 System_SR_CHS.CAB
 System_SR_CHS_wm.cab
 System_SR_CHT.CAB
 System_SR_CHT_wm.cab
 System_SR_DE.CAB
 System_SR_DE_wm.cab
 System_SR_ENU.CAB
 System_SR_ENU_wm.cab
 System_SR_ES.CAB
 System_SR_ES_wm.cab
 System_SR_FR.CAB
 System_SR_FR_wm.cab
 System_SR_IT.CAB
 System_SR_IT_wm.cab
 System_SR_JA.CAB
 System_SR_JA_wm.cab
 System_SR_KO.CAB
 System_SR_KO_wm.cab
 System_SR_pt-BR.CAB
 System_SR_pt-BR_wm.cab

For your convenience, we have provided the following installation package
(*.MSI) for use when redistributing the .NET Compact Framework dlls. Subject to
the license terms for the software, you may redistribute the MSI (unmodified) or
one or more of the separate files listed below:

.NET Compact Framework V3.5:

NETCFSetupv35.msi
 NETCFv35.wm.armv4i.cab
 NETCFv35.ppc.armv4.cab
 NETCFv35.wce.x86.cab
 NETCFv35.wce.sh4.cab
 NETCFv35.wce.mipsiv.cab
 NETCFv35.wce.mipsii.cab
 NETCFv35.wce.armv4.cab
 NETCFv35.Messages.DE.cab
 NETCFv35.Messages.DE.wm.cab
 NETCFv35.Messages.EN.cab
 NETCFv35.Messages.EN.wm.cab
 NETCFv35.Messages.ES.cab
 NETCFv35.Messages.ES.wm.cab
 NETCFv35.Messages.FR.cab
 NETCFv35.Messages.FR.wm.cab
 NETCFv35.Messages.IT.cab
 NETCFv35.Messages.IT.wm.cab
 NETCFv35.Messages.JA.cab
 NETCFv35.Messages.JA.wm.cab
 NETCFv35.Messages.KO.cab
 NETCFv35.Messages.KO.wm.cab
 NETCFv35.Messages.zh-CHS.cab
 NETCFv35.Messages.zh-CHS.wm.cab
 NETCFv35.Messages.zh-CHT.cab
 NETCFv35.Messages.zh-CHT.wm.cab


_____________________________________________________________

SQL Server Express Files

Subject to the license terms for the software, the following .EXE files may be
distributed unmodified:

SQLExpr.exe
SQLExpr32.exe

_____________________________________________________

Runtime Components

Subject to the license terms for the software, the following files may be
distributed unmodified:

Windows Installer 2.0 (InstMSIA.exe/InstMSIW.exe)   
Windows Installer 3.1 (WindowsInstaller-KB893803-v2-x86.exe)
msstdfmt.dll
vs_piaredist.exe 

_____________________________________________________

Images

One or more of the files located in the .zip file below may be distributed
subject to the license terms for the software and the additional use terms
described in the .zip file. 

VS2008ImageLibrary.zip

_____________________________________________________

Visual Studio Tools for Office Files

Subject to the license terms for the software, the following .EXE files may be
distributed unmodified:

vstor.exe
vstor30.exe
vstolp20.exe
vstor_lp_ar_30.exe

vstor_lp_da_30.exe

vstor_lp_de_30.exe

vstor_lp_es_30.exe

vstor_lp_fi_30.exe

vstor_lp_fr_30.exe

vstor_lp_he_30.exe

vstor_lp_it_30.exe

vstor_lp_ja_30.exe

vstor_lp_ko_30.exe

vstor_lp_nb-NO_30.exe

vstor_lp_nl_30.exe

vstor_lp_pl_30.exe

vstor_lp_pt-BR_30.exe

vstor_lp_ru_30.exe

vstor_lp_sv_30.exe

vstor_lp_zh-CHS_30.exe

vstor_lp_zh-CHT_30.exe

vstor_lp_zh-HK_30.exe


 

_____________________________________________________

Windows SDK Files

Subject to the license terms for the software, the following files may be
distributed unmodified:

MageUI.exe
Mage.exe
Makecert.exe
ad1.lib
amstrmid.lib
certidl.lib
CiUuid.lib
clfsmgmt.lib
daouuid.lib
DtcHelp.lib
format.lib
gpmuuid.lib
Htmlhelp.lib
KSGuid.lib
ksuser.lib
MiniDump.lib
MMC.lib
muiload.lib
odbccp32.lib
PEnter.lib
PhotoAcquireUID.lib
ScrnSave.lib
ScrnSavW.lib
strsafe.lib
taskschd.lib
unicows.lib
Uuid.lib
WindowsSideShowGuids.lib
xaSwitch.lib

____________________________________________

Report Viewer files

Subject to the license terms for the software, the following .EXE files may be
distributed unmodified:

ReportViewer.exe
ReportViewer-lp.exe

_____________________________________________________________

Visual Basic PowerPacks files

Subject to the license terms for the software, the following .EXE files may be
distributed unmodified:

VisualBasicPowerPacksSetup.exe

_____________________________________________________________

SQL Server Compact Files

The listed .msi files each install its enclosed components to a specific
location on the destination computer.  This helps to ensure serviceability and
technical support.  The .dll files enclosed in these .msi files are also
available separately in this redist.txt.  However, distributions of these
separate .dlls may result in issues of serviceability.  For more details, please
see http://go.microsoft.com/fwlink/?LinkId=94589:

SSCERuntime-ENU.msi
SSCERuntime-DEU.msi
SSCERuntime-FRA.msi
SSCERuntime-JPN.msi
SSCERuntime-RUS.msi
SSCERuntime-ESN.msi
SSCERuntime-ITA.msi
SSCERuntime-KOR.msi
SSCERuntime-CHT.msi
SSCERuntime-CHS.msi
sqlcese35.dll
sqlceqp35.dll
sqlceoledb35.dll
sqlceca35.dll
sqlceme35.dll
sqlcecompact35.dll
sqlceer35en.dll
sqlceer35cn.dll/sqlceer35zh-CHS.dll
sqlceer35de.dll 
sqlceer35es.dll 
sqlceer35fr.dll 
sqlceer35it.dll 
sqlceer35ja.dll 
sqlceer35ko.dll 
sqlceer35tw.dll/sqlceer35zh-CHT.dll 
sqlceer35ru.dll
System.Data.SqlServerCe.dll

Microsoft Synchronization Services for ADO.Net v1.0

---------------------------------------------------------------------------------

Microsoft.Synchronization.Data.dll
Microsoft.Synchronization.Data.Server.dll
Microsoft.Synchronization.Data.SqlServerCe.dll
Microsoft.Synchronization.Data.resources.dll

The listed .cab files each install its enclosed components to a specific
location on the destination mobile device.  This helps to ensure serviceability
and technical support.  The .dll and exe files enclosed in these .cab files are
also available separately in this redist.txt.  However, distributions of these
separate .dlls and exes may result in issues of serviceability.  For more
details, please see http://go.microsoft.com/fwlink/?LinkId=94589

sqlcese35.dll
sqlceqp35.dll
sqlceoledb35.dll
sqlceca35.dll
sqlceme35.dll
sqlcecompact35.dll
sqlceer35en.dll 
sqlceer35cn.dll/sqlceer35zh-CHS.dll
sqlceer35de.dll
sqlceer35es.dll 
sqlceer35fr.dll
sqlceer35it.dll 
sqlceer35ja.dll 
sqlceer35ko.dll 
sqlceer35tw.dll/sqlceer35zh-CHT.dll
sqlceer35ru.dll
System.Data.SqlServerCe.dll
System.Data.SqlClient.dll
isqlw35.exe
upgrade.exe
dbnetlib.dll

language: CHS, CHT, DE, ENU, ES, FR, IT, JA, KO, RU
device: ppc, phone
platform: wce4, wce5
processor: armv4, armv4i, mipsii, mipsii_fp, mipsiv, mipsiv_fp, sh4, x86

sql.dev.[language].[device].[platform].[processor].CAB
sql.dev.[language].[platform].[processor].CAB
sql.[device].[platform].[processor].CAB
sql.[platform].[processor].CAB
sqlce.[device].[platform].[processor].CAB
sqlce.[platform].[processor].CAB
sqlce.repl.[device].[platform].[processor].CAB
sqlce.repl.[platform].[processor].CAB
sqlce.dev.[language].[device].[platform].[processor].CAB
sqlce.dev.[language].[platform].[processor].CAB
_____________________________________________________________

Crystal Reports Files

Subject to the license terms for the software, the following .MSI files may be
distributed unmodified:

CRRedist2008_x86.msi
CRRedist2008_x64.msi
CRRedist2008_ia64.msi

== EULA.TXT ==
MICROSOFT SOFTWARE LICENSE TERMS

MICROSOFT VISUAL STUDIO 2008 STANDARD EDITION

These license terms are an agreement between Microsoft Corporation (or based on
where you live, one of its affiliates) and you.  Please read them.  They apply
to the software named above, which includes the media on which you received it,
if any.  The terms also apply to any Microsoft

·       updates, 

·       supplements, 

·       Internet-based services, and

·       support services

for this software, unless other terms accompany those items.  If so, those terms
apply.
BY USING THE SOFTWARE, YOU ACCEPT THESE TERMS.  IF YOU DO NOT ACCEPT THEM, DO
NOT USE THE SOFTWARE.  INSTEAD, RETURN IT TO THE RETAILER FOR A REFUND OR
CREDIT.  If you cannot obtain a refund there, contact Microsoft or the Microsoft
affiliate serving your country for information about Microsoft's refund
policies.  See www.microsoft.com/worldwide.  In the United States and Canada,
call (800) MICROSOFT or see www.microsoft.com/info/nareturns.htm.

AS DESCRIBED BELOW, USING SOME FEATURES ALSO OPERATES AS YOUR CONSENT TO THE
TRANSMISSION OF CERTAIN STANDARD COMPUTER INFORMATION FOR INTERNET-BASED SERVICES.

IF YOU COMPLY WITH THESE LICENSE TERMS, YOU HAVE THE RIGHTS BELOW FOR EACH
LICENSE YOU ACQUIRE.

1.      OVERVIEW.

a.      Software.  The software includes development tools, software programs and
documentation.

b.      License Model.  The software is licensed on a per user basis.

2.      INSTALLATION AND USE RIGHTS.

a.      General.  One user may install and use copies of the software to design,
develop, test and demonstrate your programs.  Testing does not include staging
on a server in a production environment, such as loading content prior to
production use.

b.      Included Microsoft Programs.  These license terms apply to all Microsoft
programs included with the software.  If the license terms with any of those
programs give you other rights that do not expressly conflict with these license
terms, you also have those rights.

c.      Third Party Programs.  The software contains third party programs.  The
license terms with those programs apply to your use of them.

3.      ADDITIONAL LICENSING REQUIREMENTS AND/OR USE RIGHTS.

a.      User Testing.  Your end users may access the software to perform acceptance
tests on your programs.

b.      Utilities.  The software contains certain components that are identified in
the Utilities List located at http://go.microsoft.com/fwlink/?LinkId=100284. 
Depending on the specific edition of the software, the number of Utility files
you receive with the software may not be equal to the number of Utilities listed
in the Utilities List.  You may copy and install the Utilities you receive with
the software on to a total of five (5) machines at any one time.  You may use
these Utilities only to debug your programs you've developed with the software.
 You must delete all the Utilities installed onto a machine within the earlier
of (i) when you have finished debugging your program; or (ii) thirty (30) days
after installation of the Utilities onto that machine.

c.      Distributable Code.  The software contains code that you are permitted to
distribute in programs you develop if you comply with the terms below.

i.      Right to Use and Distribute.  The code and text files listed below are
"Distributable Code."

·       REDIST.TXT Files.  You may copy and distribute the object code form of code
listed in REDIST.TXT files, plus any files listed on the REDIST list located at:
http://go.microsoft.com/fwlink/?LinkID=100327&clcid=0x409.

·       Sample Code.  You may modify, copy, and distribute the source and object code
form of code marked as "sample."

·       Microsoft Merge Modules.  You may copy and distribute the unmodified output of
Microsoft Merge Modules.

·       MFCs, ATLs and CRTs.  You may modify the source code form of Microsoft
Foundation Classes (MFCs), Active Template Libraries (ATLs), and C runtimes
(CRTs) to design, develop and test your programs, and copy and distribute the
object code form of your modified files under a new name.

·       Image Library.  You may copy and distribute images and animations in the Image
Library as described in the software documentation.  You may also modify that
content.  If you modify the content, it must be for use that is consistent with
the permitted use of the unmodified content.

·               Third Party Distribution.  You may permit distributors of your programs to
copy and distribute the Distributable Code as part of those programs.

ii.     Distribution Requirements.  For any Distributable Code you distribute, you must

·               add significant primary functionality to it in your programs;

·       for any Distributable Code having a filename extension of .lib, distribute
only the results of running such Distributable Code through a linker with your
program;

·       distribute Distributable Code included in a setup program only as part of that
setup program without modification;

·               require distributors and external end users to agree to terms that protect it
at least as much as this agreement;

·               display your valid copyright notice on your programs; and

·               indemnify, defend, and hold harmless Microsoft from any claims, including
attorneys' fees, related to the distribution or use of your programs.

iii.    Distribution Restrictions.  You may not

·               alter any copyright, trademark or patent notice in the Distributable Code;

·               use Microsoft's trademarks in your programs' names or in a way that suggests
your programs come from or are endorsed by Microsoft;

·               distribute Distributable Code to run on a platform other than Microsoft
operating systems, run-time technologies or application platforms;

·               include Distributable Code in malicious, deceptive or unlawful programs; or

·               modify or distribute the source code of any Distributable Code so that any
part of it becomes subject to an Excluded License.  An Excluded License is one
that requires, as a condition of use, modification or distribution, that

·       the code be disclosed or distributed in source code form; or

·       others have the right to modify it.

4.      INTERNET-BASED SERVICES.  Microsoft provides Internet-based services with the
software.  It may change or cancel them at any time.

a.      Consent for Internet-Based Services.  The software features described below
connect to Microsoft or service provider computer systems over the Internet.  In
some cases, you will not receive a separate notice when they connect.  BY USING
THESE FEATURES, YOU CONSENT TO THE TRANSMISSION OF THIS INFORMATION.  Microsoft
does not use the information to identify or contact you.

Computer Information.  The following features use Internet protocols, which send
to the appropriate systems computer information, such as your Internet protocol
address, the type of operating system, browser and name and version of the
software you are using, and the language code of the device where you installed
the software.  Microsoft uses this information to make the Internet-based
services available to you.

·       Windows Update Feature.  You may connect new hardware to the device where you
installed the software.  Your device may not have the drivers needed to
communicate with that hardware.  If so, the update feature of the software can
obtain the correct driver from Microsoft and install it on your device.  You can
switch off this update feature.

·       Web Content Features.  Features in the software can retrieve related content
from Microsoft and provide it to you.  To provide the content, these features
send to Microsoft the type of operating system, name and version of the software
you are using, type of browser and language code of the device where you
installed the software.  Examples of these features are clip art, templates,
online training, online assistance and Appshelp.  You may choose not to use
these web content features.

·       Real Simple Syndication ("RSS") Feed.  This software start page contains
updated content that is supplied by means of an RSS feed online from Microsoft.

b.      Misuse of Internet-based Services.  You may not use these services in any way
that could harm them or impair anyone else's use of them.  You may not use the
services to try to gain unauthorized access to any service, data, account or
network by any means.

5.      PRODUCT KEYS.  The software requires a key to install or access it.  You are
responsible for the use of keys assigned to you.  You should not share the keys
with third parties.

6.      SQL SERVER BENCHMARK TESTING.  You must obtain Microsoft's prior written
approval to disclose to a third party the results of any benchmark test of the
SQL Server software that accompanies this software. 

7.      .NET FRAMEWORK SOFTWARE.  The software contains Microsoft .NET Framework
software.  This software is part of Windows.  The license terms for Windows
apply to your use of the .NET Framework software.

8.      MICROSOFT .NET FRAMEWORK BENCHMARK TESTING.  The software includes the .NET
Framework component of the Windows operating systems (".NET Component").  You
may conduct internal benchmark testing of the .NET Component.  You may disclose
the results of any benchmark test of the .NET Component, provided that you
comply with the following terms:  (1) you must disclose all the information
necessary for replication of the tests, including complete and accurate details
of your benchmark testing methodology,  the test scripts/cases, tuning
parameters applied, hardware and software platforms tested, the name and version
number of any third party testing tool used to conduct the testing, and complete
source code for the benchmark suite/harness that is developed by or for you and
used to test both the .NET Component and the competing implementation(s); (2)
you must disclose the date (s) that you conducted the benchmark tests, along
with specific version information for all Microsoft software products tested,
including the .NET Component; (3) your benchmark testing was performed using all
performance tuning and best practice guidance set forth in the product
documentation and/or on Microsoft's support web sites, and uses the latest
updates, patches and fixes available for the .NET Component and the relevant
Microsoft operating system; (4) it shall be sufficient if you make the
disclosures provided for above at a publicly available location such as a
website, so long as every public disclosure of the results of your benchmark
test expressly identifies the public site containing all required disclosures;
and (5) nothing in this provision shall be deemed to waive any other right that
you may have to conduct benchmark testing.  The foregoing obligations shall not
apply to your disclosure of the results of any customized benchmark test of the
.NET Component, whereby such disclosure is made under confidentiality in
conjunction with a bid request by a prospective customer, such customer's
application(s) are specifically tested and the results are only disclosed to
such specific customer.  Notwithstanding any other agreement you may have with
Microsoft, if you disclose such benchmark test results, Microsoft shall have the
right to disclose the results of benchmark tests it conducts of your products
that compete with the .NET Component, provided it complies with the same
conditions above.

9.      SCOPE OF LICENSE.  The software is licensed, not sold. This agreement only
gives you some rights to use the software.  Microsoft reserves all other rights.
 Unless applicable law gives you more rights despite this limitation, you may
use the software only as expressly permitted in this agreement.  In doing so,
you must comply with any technical limitations in the software that only allow
you to use it in certain ways.  For more information, see
www.microsoft.com/licensing/userights.  You may not

·       work around any technical limitations in the software;

·       reverse engineer, decompile or disassemble the software, except and only to
the extent that applicable law expressly permits, despite this limitation;

·       make more copies of the software than specified in this agreement or allowed
by applicable law, despite this limitation;

·       publish the software for others to copy;

·       rent, lease or lend the software; or

·       use the software for commercial software hosting services.

10.     WINDOWS MOBILE COMPONENTS.   

a.      You may only use the Windows Mobile components located in the "\Program
Files\Windows Mobile 5.0 SDK R2\" directory to create programs that run on
Windows Mobile 5.0 software for Pocket PC or Windows Mobile 5.0 software for the
Smartphone. 

b.      Program Application and Driver Limitations.  You may not use or include any
of the Windows Mobile components located in the "\Program Files\Windows Mobile
5.0 SDK R2\" directory, nor any components thereof, in the development of any
program application, device or system in which a malfunction would result in a
foreseeable risk of personal injury, death or severe physical or environmental
damage.

11.     BACKUP COPY.  You may make one backup copy of the software.  You may use it
only to reinstall the software.

12.     DOCUMENTATION.  Any person that has valid access to your computer or
internal network may copy and use the documentation for your internal, reference
purposes.

13.     NOT FOR RESALE SOFTWARE.  You may not sell software marked as "NFR" or "Not
for Resale."

14.     ACADEMIC EDITION SOFTWARE.  You must be a "Qualified Educational User" to
use software marked as "Academic Edition" or "AE."   If you do not know whether
you are a Qualified Educational User, visit www.microsoft.com/education or
contact the Microsoft affiliate serving your country.

15.     UPGRADE.  If this software is marked as an upgrade version, you may use it
only if you have a license to use the software eligible for upgrade.  If you
upgrade, this software takes the place of the earlier version, and this
agreement takes the place of the agreement for that earlier version.  You may
use the earlier version only as permitted in the Downgrade clause below.  

16.     DOWNGRADE.  You may install and use this version and an earlier version of
the software at the same time.  This agreement applies to your use of the
earlier version.  If the earlier version includes different components, any
terms for those components in the agreement that comes with the earlier version
apply to your use of them.  Microsoft is not obligated to supply earlier
versions to you.

17.     TRANSFER TO A THIRD PARTY.  The first user of the software may transfer it,
and this agreement, directly to a third party.  Before the transfer, that party
must agree that this agreement applies to the transfer and use of the software.
 The first user must uninstall the software before transferring it separately
from the device.  The first user may not retain any copies.

18.     EXPORT RESTRICTIONS.  The software is subject to United States export laws
and regulations.  You must comply with all domestic and international export
laws and regulations that apply to the software.  These laws include
restrictions on destinations, end users and end use.  For additional
information, see www.microsoft.com/exporting.

19.     SUPPORT SERVICES.  Microsoft provides support services for the software as
described at www.support.microsoft.com/common/international.aspx.

20.     ENTIRE AGREEMENT.  This agreement (including the warranty below), and the
terms for supplements, updates, Internet-based services and support services
that you use, are the entire agreement for the software and support services.

21.     APPLICABLE LAW.

a.      United States.  If you acquired the software in the United States, Washington
state law governs the interpretation of this agreement and applies to claims for
breach of it, regardless of conflict of laws principles.  The laws of the state
where you live govern all other claims, including claims under state consumer
protection laws, unfair competition laws, and in tort.

b.      Outside the United States.  If you acquired the software in any other
country, the laws of that country apply.

22.     LEGAL EFFECT.  This agreement describes certain legal rights.  You may have
other rights under the laws of your state or country.  You may also have rights
with respect to the party from whom you acquired the software.  This agreement
does not change your rights under the laws of your state or country if the laws
of your state or country do not permit it to do so.

23.     LIMITATION ON AND EXCLUSION OF DAMAGES.  YOU CAN RECOVER FROM MICROSOFT AND
ITS SUPPLIERS ONLY DIRECT DAMAGES UP TO THE AMOUNT YOU PAID FOR THE SOFTWARE.
YOU CANNOT RECOVER ANY OTHER DAMAGES, INCLUDING CONSEQUENTIAL, LOST PROFITS,
SPECIAL, INDIRECT OR INCIDENTAL DAMAGES.

This limitation applies to

·       anything related to the software, services, content (including code) on third
party Internet sites, or third party programs; and

·       claims for breach of contract, breach of warranty, guarantee or condition,
strict liability, negligence, or other tort to the extent permitted by
applicable law.
It also applies even if

·       repair, replacement or a refund for the software does not fully compensate you
for any losses; or

·       Microsoft knew or should have known about the possibility of the damages.
Some states do not allow the exclusion or limitation of incidental or
consequential damages, so the above limitation or exclusion may not apply to
you.  They also may not apply to you because your country may not allow the
exclusion or limitation of incidental, consequential or other damages. 

**************************************************************************************

LIMITED WARRANTY

A.      LIMITED WARRANTY.  If you follow the instructions, the software will perform
substantially as described in the Microsoft materials that you receive in or
with the software.

B.      TERM OF WARRANTY; WARRANTY RECIPIENT; LENGTH OF ANY IMPLIED WARRANTIES.  THE
LIMITED WARRANTY COVERS THE SOFTWARE FOR ONE YEAR AFTER ACQUIRED BY THE FIRST
USER.  IF YOU RECEIVE SUPPLEMENTS, UPDATES, OR REPLACEMENT SOFTWARE DURING THAT
YEAR, THEY WILL BE COVERED FOR THE REMAINDER OF THE WARRANTY OR 30 DAYS,
WHICHEVER IS LONGER.  If the first user transfers the software, the remainder of
the warranty will apply to the recipient.
TO THE EXTENT PERMITTED BY LAW, ANY IMPLIED WARRANTIES, GUARANTEES OR CONDITIONS
LAST ONLY DURING THE TERM OF THE LIMITED WARRANTY.  Some states do not allow
limitations on how long an implied warranty lasts, so these limitations may not
apply to you.  They also might not apply to you because some countries may not
allow limitations on how long an implied warranty, guarantee or condition lasts.

C.      EXCLUSIONS FROM WARRANTY.  This warranty does not cover problems caused by
your acts (or failures to act), the acts of others, or events beyond Microsoft's
reasonable control.

D.      REMEDY FOR BREACH OF WARRANTY.  MICROSOFT WILL REPAIR OR REPLACE THE SOFTWARE
AT NO CHARGE.  IF MICROSOFT CANNOT REPAIR OR REPLACE IT, MICROSOFT WILL REFUND
THE AMOUNT SHOWN ON YOUR RECEIPT FOR THE SOFTWARE.  IT WILL ALSO REPAIR OR
REPLACE SUPPLEMENTS, UPDATES AND REPLACEMENT SOFTWARE AT NO CHARGE.  IF
MICROSOFT CANNOT REPAIR OR REPLACE THEM, IT WILL REFUND THE AMOUNT YOU PAID FOR
THEM, IF ANY.  YOU MUST UNINSTALL THE SOFTWARE AND RETURN ANY MEDIA AND OTHER
ASSOCIATED MATERIALS TO MICROSOFT WITH PROOF OF PURCHASE TO OBTAIN A REFUND. 
THESE ARE YOUR ONLY REMEDIES FOR BREACH OF THE LIMITED WARRANTY.

E.      CONSUMER RIGHTS NOT AFFECTED.  YOU MAY HAVE ADDITIONAL CONSUMER RIGHTS UNDER
YOUR LOCAL LAWS, WHICH THIS AGREEMENT CANNOT CHANGE.

F.      WARRANTY PROCEDURES.  You need proof of purchase for warranty service.

1.      United States and Canada.  For warranty service or information about how to
obtain a refund for software acquired in the United States and Canada, contact
Microsoft at

·       (800) MICROSOFT;

·       Microsoft Customer Service and Support, One Microsoft Way, Redmond, WA
98052-6399; or

·       visit www.microsoft.com/info/nareturns.htm.

2.      Europe, Middle East and Africa.  If you acquired the software in Europe, the
Middle East or Africa, Microsoft Ireland Operations Limited makes this limited
warranty.  To make a claim under this warranty, you should contact either

·       Microsoft Ireland Operations Limited, Customer Care Centre, Atrium Building
Block B, Carmanhall Road, Sandyford Industrial Estate, Dublin 18, Ireland; or

·       the Microsoft affiliate serving your country (see www.microsoft.com/worldwide).

3.      Outside United States, Canada, Europe, Middle East and Africa.  If you
acquired the software outside the United States, Canada, Europe, the Middle East
and Africa, contact the Microsoft affiliate serving your country (see
www.microsoft.com/worldwide).

G.      NO OTHER WARRANTIES.  THE LIMITED WARRANTY IS THE ONLY DIRECT WARRANTY FROM
MICROSOFT.  MICROSOFT GIVES NO OTHER EXPRESS WARRANTIES, GUARANTEES OR
CONDITIONS.  WHERE ALLOWED BY YOUR LOCAL LAWS, MICROSOFT EXCLUDES IMPLIED
WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NON-INFRINGEMENT.  If your local laws give you any implied warranties,
guarantees or conditions, despite this exclusion, your remedies are described in
the Remedy for Breach of Warranty clause above, to the extent permitted by your
local laws.

H.      LIMITATION ON AND EXCLUSION OF DAMAGES FOR BREACH OF WARRANTY.  THE
LIMITATION ON AND EXCLUSION OF DAMAGES CLAUSE ABOVE APPLIES TO BREACHES OF THIS
LIMITED WARRANTY.
THIS WARRANTY GIVES YOU SPECIFIC LEGAL RIGHTS, AND YOU MAY ALSO HAVE OTHER
RIGHTS WHICH VARY FROM STATE TO STATE.  YOU MAY ALSO HAVE OTHER RIGHTS WHICH
VARY FROM COUNTRY TO COUNTRY.
%% The following software may be included in this product:  ANTLR Java runtime binary only jar. Use of any of this software is governed by the terms of the license below: 

ANTLR 3 License

[The BSD License]
Copyright (c) 2003-2007, Terence Parr
All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

    * Redistributions of source code must retain the above copyright notice,
this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright notice,
this list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.
    * Neither the name of the author nor the names of its contributors may be
used to endorse or promote products derived from this software without specific
prior written permission. 

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
%% The following software may be included in this product:  Microsoft DirectShow - Base Classes. Use of any of this software is governed by the terms of the license below: 

MSDN - Information on Terms of Use

Updated: February 13, 2008

ON THIS PAGE

    * ACCEPTANCE OF TERMS
    * PRIVACY AND PROTECTION OF PERSONAL INFORMATION
    * NOTICE SPECIFIC TO APIs AVAILABLE ON THIS WEB SITE
    * NOTICE SPECIFIC TO SOFTWARE AVAILABLE ON THIS WEB SITE
    * NOTICE SPECIFIC TO DOCUMENTATION AVAILABLE ON THIS WEB SITE
    * NOTICES REGARDING SOFTWARE, DOCUMENTATION, APIS AND SERVICES AVAILABLE ON
THIS WEB SITE
    * RESERVATION OF RIGHTS
    * MEMBER ACCOUNT, PASSWORD, AND SECURITY
    * NO UNLAWFUL OR PROHIBITED USE
    * USE OF SERVICES
    * MATERIALS PROVIDED TO MICROSOFT OR POSTED AT ANY MICROSOFT WEB SITE
    * NOTICES AND PROCEDURE FOR MAKING CLAIMS OF COPYRIGHT INFRINGEMENT
    * LINKS TO THIRD PARTY SITES
    * UNSOLICITED IDEA SUBMISSION POLICY
    * COPYRIGHT NOTICE & FAQ
    * TRADEMARKS

ACCEPTANCE OF TERMS.

Microsoft provides you with access to a variety of resources on this website
("Web Site"), including documentation and other product information
(collectively the "Documentation"), download areas, communication forums, and
other services (collectively "Services"), software, including developer tools
and sample code (collectively "Software"), and Application Program Interface
information ("APIs").  The Documentation, Services, Software, and APIs
(including any updates, enhancements, new features, and/or the addition of any
new Web properties to the Web Site), are subject to the following Terms of Use
("TOU"), unless we have provided those items to you under more specific terms,
in which case, those more specific terms will apply to the relevant item.
Microsoft reserves the right to update the TOU at any time without notice to
you. The most current version of the TOU can be reviewed by clicking on the
"Terms of Use" hypertext link located at the bottom of our Web pages.

Top of page
PRIVACY AND PROTECTION OF PERSONAL INFORMATION.

See the Privacy Statement disclosures relating to the collection and use of your
information.

Top of page
NOTICE SPECIFIC TO APIS AVAILABLE ON THIS WEB SITE.

Microsoft publishes information on a number of APIs on this Web Site.  Microsoft
will not assert any of its patent rights on account of your products calling
these APIs in order to receive services from the Microsoft product that exposes
the APIs. 

Top of page
NOTICE SPECIFIC TO SOFTWARE AVAILABLE ON THIS WEB SITE.

All Software is the copyrighted work of Microsoft and/or its suppliers. Use of
the Software is governed by the terms of the end user license agreement, if any,
which accompanies or is included with the Software ("License Agreement").

If Microsoft makes Software available on this Web Site without a License
Agreement, you may use such Software to design, develop and test your programs
to run on Microsoft products and services.

If Microsoft makes any code marked as "sample" available on this Web Site
without a License Agreement, then that code is licensed to you under the terms
of the Microsoft Limited Public License.

The Software is made available for download solely for use by end users
according to the License Agreement or these TOU. Any reproduction or
redistribution of the Software not in accordance with the License Agreement or
these TOU is expressly prohibited.

WITHOUT LIMITING THE FOREGOING, COPYING OR REPRODUCTION OF THE SOFTWARE TO ANY
OTHER SERVER OR LOCATION FOR FURTHER REPRODUCTION OR REDISTRIBUTION IS EXPRESSLY
PROHIBITED, UNLESS SUCH REPRODUCTION OR REDISTRIBUTION IS EXPRESSLY PERMITTED BY
THE LICENSE AGREEMENT ACCOMPANYING SUCH SOFTWARE.

FOR YOUR CONVENIENCE, MICROSOFT MAY MAKE AVAILABLE ON THIS WEB SITE, TOOLS AND
UTILITIES FOR USE AND/OR DOWNLOAD. MICROSOFT DOES NOT MAKE ANY ASSURANCES WITH
REGARD TO THE ACCURACY OF THE RESULTS OR OUTPUT THAT DERIVES FROM SUCH USE OF
ANY SUCH TOOLS AND UTILITIES. PLEASE RESPECT THE INTELLECTUAL PROPERTY RIGHTS OF
OTHERS WHEN USING THE TOOLS AND UTILITIES MADE AVAILABLE ON THIS WEB SITE.

RESTRICTED RIGHTS LEGEND. Any Software which is downloaded from the Web Site for
or on behalf of the United States of America, its agencies and/or
instrumentalities ("U.S. Government"), is provided with Restricted Rights. Use,
duplication, or disclosure by the U.S. Government is subject to restrictions as
set forth in subparagraph (c)(1)(ii) of the Rights in Technical Data and
Computer Software clause at DFARS 252.227-7013 or subparagraphs (c)(1) and (2)
of the Commercial Computer Software - Restricted Rights at 48 CFR 52.227-19, as
applicable. Manufacturer is Microsoft Corporation, One Microsoft Way, Redmond,
WA 98052-6399.

Top of page
NOTICE SPECIFIC TO DOCUMENTATION AVAILABLE ON THIS WEB SITE.

All Documentation is the copyrighted work of Microsoft and/or its suppliers. Use
of the Documentation is governed by the terms of the license agreement, if any,
which accompanies or is included with the Documentation ("Documentation License
Agreement").

If Documentation is made available to you on this Web Site without a
Documentation License Agreement, then You may annotate, translate, and make a
reasonable number of copies of the Documentation for your internal use in
designing, developing, and testing your software, products and services, and you
may distribute a reasonable amount of portions of the Documentation as necessary
to document your software, products, and services.  You may not publish any such
annotations or translations.  You must preserve the below copyright notice in
all copies of the Documentation and ensure that both the copyright notice and
this permission notice appear in those copies.  Accredited educational
institutions, such as K-12, universities, private/public colleges, and state
community colleges, may download and reproduce the Documentation for
distribution in the classroom. Distribution outside the classroom requires
express written permission. Use for any other purpose is expressly prohibited

The Documentation does not include the design or layout of the Microsoft.com Web
site or any other Microsoft owned, operated, licensed or controlled site.
Elements of Microsoft Web sites are protected by trade dress, trademark, unfair
competition, and other laws and may not be copied or imitated in whole or in
part. No logo, graphic, sound or image from any Microsoft Web site may be copied
or retransmitted unless expressly permitted by Microsoft.

THE DOCUMENTATION AND RELATED GRAPHICS PUBLISHED ON THE WEB SITE COULD INCLUDE
TECHNICAL INACCURACIES OR TYPOGRAPHICAL ERRORS. CHANGES ARE PERIODICALLY ADDED
TO THE INFORMATION IN THIS WEB SITE. MICROSOFT AND/OR ITS RESPECTIVE SUPPLIERS
MAY MAKE IMPROVEMENTS AND/OR CHANGES IN THE PRODUCT(S) AND/OR THE PROGRAM(S)
DESCRIBED IN THIS WEB SITE AT ANY TIME.

Top of page
NOTICES REGARDING SOFTWARE, DOCUMENTATION, APIS AND SERVICES AVAILABLE ON THIS
WEB SITE.

THE SOFTWARE, DOCUMENTATION, APIS, AND SERVICES ARE WARRANTED, IF AT ALL, ONLY
ACCORDING TO THE TERMS OF ASEPARATE AGREEMENT THAT COVERS THE APPLICABLE
SOFTWARE, DOCUMENTATION, APIS, OR SERVICES. EXCEPT AS WARRANTED IN THAT SEPARATE
AGREEMENT (IF ANY), MICROSOFT CORPORATION HEREBY DISCLAIMS ALL WARRANTIES AND
CONDITIONS WITH REGARD TO THE SOFTWARE, DOCUMENTATION, APIS, AND SERVICES,
INCLUDING ALL WARRANTIES AND CONDITIONS OF MERCHANTABILITY, WHETHER EXPRESS,
IMPLIED OR STATUTORY, FITNESS FOR A PARTICULAR PURPOSE, TITLE AND NON-INFRINGEMENT.

IN NO EVENT SHALL MICROSOFT AND/OR ITS RESPECTIVE SUPPLIERS BE LIABLE FOR ANY
SPECIAL, INDIRECT OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING
FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE
OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
PERFORMANCE OF SOFTWARE, DOCUMENTATION, APIS, PROVISION OF OR FAILURE TO PROVIDE
SERVICES, OR INFORMATION AVAILABLE FROM ANY OF THE FOREGOING SOFTWARE,
DOCUMENTATION, APIS OR SERVICES.

Top of page
RESERVATION OF RIGHTS.

Microsoft reserves all rights not expressly granted under these TOU, and no
other rights are granted under these TOU by implication or estoppel or otherwise. 

Top of page
MEMBER ACCOUNT, PASSWORD, AND SECURITY.

If any of the Services requires you to open an account, you must complete the
registration process by providing us with current, complete and accurate
information as prompted by the applicable registration form. You also will
choose a password and a user name. You are entirely responsible for maintaining
the confidentiality of your password and account. Furthermore, you are entirely
responsible for any and all activities that occur under your account. You agree
to notify Microsoft immediately of any unauthorized use of your account or any
other breach of security. Microsoft will not be liable for any loss that you may
incur as a result of someone else using your password or account, either with or
without your knowledge. However, you could be held liable for losses incurred by
Microsoft or another party due to someone else using your account or password.
You may not use anyone else's account at any time, without the permission of the
account holder.

Top of page
NO UNLAWFUL OR PROHIBITED USE.

As a condition of your use of the Services, you will not use the Services for
any purpose that is unlawful or prohibited by these terms, conditions, and
notices. You may not use the Services in any manner that could damage, disable,
overburden, or impair any Microsoft server, or the network(s) connected to any
Microsoft server, or interfere with any other party's use and enjoyment of any
Services. You may not attempt to gain unauthorized access to any Services, other
accounts, computer systems or networks connected to any Microsoft server or to
any of the Services, through hacking, password mining or any other means. You
may not obtain or attempt to obtain any materials or information through any
means not intentionally made available through the Services.

Top of page
USE OF SERVICES.

The Services may contain e-mail services, bulletin board services, chat areas,
news groups, forums, communities, personal web pages, calendars, photo albums,
file cabinets and/or other message or communication facilities designed to
enable you to communicate with others (each a "Communication Service" and
collectively "Communication Services"). You agree to use the Communication
Services only to post, send and receive messages and material that are proper
and, when applicable, related to the particular Communication Service. By way of
example, and not as a limitation, you agree that when using the Communication
Services, you will not:

    * Use the Communication Services in connection with surveys, contests,
pyramid schemes, chain letters, junk email, spamming or any duplicative or
unsolicited messages (commercial or otherwise).
    * Defame, abuse, harass, stalk, threaten or otherwise violate the legal
rights (such as rights of privacy and publicity) of others.
    * Publish, post, upload, distribute or disseminate any inappropriate,
profane, defamatory, obscene, indecent or unlawful topic, name, material or
information.
    * Upload, or otherwise make available, files that contain images,
photographs, software or other material protected by intellectual property laws,
including, by way of example, and not as limitation, copyright or trademark laws
(or by rights of privacy or publicity) unless you own or control the rights
thereto or have received all necessary consent to do the same.
    * Use any material or information, including images or photographs, which
are made available through the Services in any manner that infringes any
copyright, trademark, patent, trade secret, or other proprietary right of any party.
    * Upload files that contain viruses, Trojan horses, worms, time bombs,
cancelbots, corrupted files, or any other similar software or programs that may
damage the operation of another's computer or property of another.
    * Advertise or offer to sell or buy any goods or services for any business
purpose, unless such Communication Services specifically allows such messages.
    * Download any file posted by another user of a Communication Service that
you know, or reasonably should know, cannot be legally reproduced, displayed,
performed, and/or distributed in such manner.
    * Falsify or delete any copyright management information, such as author
attributions, legal or other proper notices or proprietary designations or
labels of the origin or source of software or other material contained in a file
that is uploaded.
    * Restrict or inhibit any other user from using and enjoying the
Communication Services.
    * Violate any code of conduct or other guidelines which may be applicable
for any particular Communication Service.
    * Harvest or otherwise collect information about others, including e-mail
addresses.
    * Violate any applicable laws or regulations.
    * Create a false identity for the purpose of misleading others.
    * Use, download or otherwise copy, or provide (whether or not for a fee) to
a person or entity any directory of users of the Services or other user or usage
information or any portion thereof.

Microsoft has no obligation to monitor the Communication Services. However,
Microsoft reserves the right to review materials posted to the Communication
Services and to remove any materials in its sole discretion. Microsoft reserves
the right to terminate your access to any or all of the Communication Services
at any time, without notice, for any reason whatsoever.

Microsoft reserves the right at all times to disclose any information as
Microsoft deems necessary to satisfy any applicable law, regulation, legal
process or governmental request, or to edit, refuse to post or to remove any
information or materials, in whole or in part, in Microsoft's sole discretion.

Always use caution when giving out any personally identifiable information about
yourself or your children in any Communication Services. Microsoft does not
control or endorse the content, messages or information found in any
Communication Services and, therefore, Microsoft specifically disclaims any
liability with regard to the Communication Services and any actions resulting
from your participation in any Communication Services. Managers and hosts are
not authorized Microsoft spokespersons, and their views do not necessarily
reflect those of Microsoft.

Materials uploaded to the Communication Services may be subject to posted
limitations on usage, reproduction and/or dissemination; you are responsible for
adhering to such limitations if you download the materials.

Top of page
MATERIALS PROVIDED TO MICROSOFT OR POSTED AT ANY MICROSOFT WEB SITE.

Microsoft does not claim ownership of the materials you provide to Microsoft
(including feedback and suggestions) or post, upload, input or submit to any
Services or its associated services for review by the general public, or by the
members of any public or private community, (each a "Submission" and
collectively "Submissions"). However, by posting, uploading, inputting,
providing or submitting ("Posting") your Submission you are granting Microsoft,
its affiliated companies, necessary sublicensees (including third parties whose
products , technologies and services use or interface with any specific parts of
a Microsoft software or service that includes the Submission) , without charge,
the right  to use, share and commercialize your Submission in any way and for
any purpose. You will not give any Submission that is subject to a license that
requires Microsoft to license its software or documentation to third parties
because we include your Submission in them.

Microsoft is under no obligation to post or use any Submission you may provide,
and Microsoft may remove any Submission at any time in its sole discretion.

By Posting a Submission you warrant and represent that you own or otherwise
control all of the rights to your Submission as described in these TOU
including, without limitation, all the rights necessary for you to Post the
Submissions.

In addition to the warranty and representation set forth above, by Posting a
Submission that contains images, photographs, pictures or that are otherwise
graphical in whole or in part ("Images"), you warrant and represent that (a) you
are the copyright owner of such Images, or that the copyright owner of such
Images has granted you permission to use such Images or any content and/or
images contained in such Images consistent with the manner and purpose of your
use and as otherwise permitted by these TOU, (b) you have the rights necessary
to grant the licenses and sublicenses described in these TOU, and (c) that each
person depicted in such Images, if any, has provided consent to the use of the
Images as set forth in these TOU, including, by way of example, and not as a
limitation, the distribution, public display and reproduction of such Images. By
Posting Images, you are granting (a) to all members of your private community
(for each such Images available to members of such private community), and/or
(b) to the general public (for each such Images available anywhere on the
Services or Web Site, other than a private community), permission to use your
Images in connection with the use, as permitted by these TOU, of any of the
Services or Web Site, (including, by way of example, and not as a limitation,
making prints and gift items which include such Images), and including, without
limitation, a non-exclusive, world-wide, royalty-free license to: copy,
distribute, transmit, publicly display, publicly perform, reproduce, edit,
translate and reformat your Images without having your name attached to such
Images, and the right to sublicense such rights to any supplier of the Services.
The licenses granted in the preceding sentences for a Images will terminate at
the time you completely remove such Images from the Services or Web Site,
provided that such termination shall not affect any licenses granted in
connection with such Images prior to the time you completely remove such Images.
No compensation will be paid with respect to the use of your Images.

Top of page
NOTICES AND PROCEDURE FOR MAKING CLAIMS OF COPYRIGHT INFRINGEMENT.

Pursuant to Title 17, United States Code, Section 512(c)(2), notifications of
claimed copyright infringement should be sent to Service Provider's Designated
Agent. ALL INQUIRIES NOT RELEVANT TO THE FOLLOWING PROCEDURE WILL NOT RECEIVE A
RESPONSE.

See Notice and Procedure for Making Claims of Copyright Infringement.

Top of page
LINKS TO THIRD PARTY SITES.

THE LINKS IN THIS AREA WILL LET YOU LEAVE MICROSOFT'S SITE. THE LINKED SITES ARE
NOT UNDER THE CONTROL OF MICROSOFT AND MICROSOFT IS NOT RESPONSIBLE FOR THE
CONTENTS OF ANY LINKED SITE OR ANY LINK CONTAINED IN A LINKED SITE, OR ANY
CHANGES OR UPDATES TO SUCH SITES. MICROSOFT IS NOT RESPONSIBLE FOR WEBCASTING OR
ANY OTHER FORM OF TRANSMISSION RECEIVED FROM ANY LINKED SITE. MICROSOFT IS
PROVIDING THESE LINKS TO YOU ONLY AS A CONVENIENCE, AND THE INCLUSION OF ANY
LINK DOES NOT IMPLY ENDORSEMENT BY MICROSOFT OF THE SITE.

Top of page
UNSOLICITED IDEA SUBMISSION POLICY.

MICROSOFT OR ANY OF ITS EMPLOYEES DO NOT ACCEPT OR CONSIDER UNSOLICITED IDEAS,
INCLUDING IDEAS FOR NEW ADVERTISING CAMPAIGNS, NEW PROMOTIONS, NEW PRODUCTS OR
TECHNOLOGIES, PROCESSES, MATERIALS, MARKETING PLANS OR NEW PRODUCT NAMES. PLEASE
DO NOT SEND ANY ORIGINAL CREATIVE ARTWORK, SAMPLES, DEMOS, OR OTHER WORKS. THE
SOLE PURPOSE OF THIS POLICY IS TO AVOID POTENTIAL MISUNDERSTANDINGS OR DISPUTES
WHEN MICROSOFT'S PRODUCTS OR MARKETING STRATEGIES MIGHT SEEM SIMILAR TO IDEAS
SUBMITTED TO MICROSOFT. SO, PLEASE DO NOT SEND YOUR UNSOLICITED IDEAS TO
MICROSOFT OR ANYONE AT MICROSOFT. IF, DESPITE OUR REQUEST THAT YOU NOT SEND US
YOUR IDEAS AND MATERIALS, YOU STILL SEND THEM, PLEASE UNDERSTAND THAT MICROSOFT
MAKES NO ASSURANCES THAT YOUR IDEAS AND MATERIALS WILL BE TREATED AS
CONFIDENTIAL OR PROPRIETARY.

Top of page
COPYRIGHT NOTICE & FAQ.

© 2008 Microsoft Corporation. All rights reserved.

The following is provided for informational purposes only and should not be
construed as legal advice. If you need legal advice, contact a lawyer.

What is copyright?

Copyright law protects original works, such as websites, books, music,
paintings, photos and video. A work is "original" if it contains some elements
you created and did not borrow from others. Typically, when you create an
original work, you own the copyright. As the copyright owner, you can control
how others use your work. For example, if you write a movie script, you have the
right to, and can prevent others from, copying your script, sharing it with
others ("distributing it"), making a movie or book from your script (a
"derivative work"), or publicly performing your script as a play or movie. You
also have the ability to sell or give away these rights. In other words, you
could sell the right to make a movie based on your script to a movie studio.

If you use someone else's copyrighted materials without permission, that use
generally violates the copyright owner's exclusive rights, and is copyright
infringement. So if you create a new work and include parts of other people's
works in it (such as an existing photo, lengthy quotes from a book or a loop
from a song), you must own or have permission to use the elements you borrow.
For example, if your script is based on an existing popular series, you should
obtain permission to use the elements you borrow from the series.

Copyright law is different from the law of personal property. If you buy a
physical object, such as a movie on DVD, you own the physical object. You do
not, however, obtain ownership of the "copyrights" (the rights to make copies,
distribute, make derivatives and publicly perform or display) in the content of
the movie. The fact that you have obtained physical possession of a DVD does not
automatically grant you the right to copy or share it.

If you make your own movie, it may include many copyrighted works in it. So, if
you decide to make a movie based on your script, you must either create all
elements of it on your own, or have permission to use the elements you borrow.
Especially keep in mind that photos or artwork hanging on the walls of your sets
and music on the soundtrack (even if you own the CD or MP3) may be copyrighted.
You should not include copyrighted works such as these in your movie without
authorization.

A few other things to keep in mind are:

   1. Just because a work does not include a copyright notice (e.g., © 2006
Microsoft Corporation) does not mean the work is in the public domain. Copyright
notices are generally not required for works to be protected by copyright.
   2. Just because a work is easily available on the internet or elsewhere does
not mean you may use the work freely. Look for terms of use, such as Creative
Commons, that explain how works you find on the Internet may be used.

Isn't it in the public domain?

Just because a work is freely available, does not mean it is in the "public
domain." Copyright is for a limited term; it does not last forever. In the
copyright context, "public domain" means the copyright term has expired. Once a
work is in the public domain, it may be freely used without permission from the
copyright owner.

Determining the term of copyright can be complex, particularly because copyright
laws vary from country to country. Also, even if the copyright on a work has
expired, you should be careful about how you use a public domain work. For
example, a book may be in the public domain, but it might not be ok to scan the
book cover to cover and post it on the internet. This is because the particular
version of the book may contain new copyrightable material that is not in the
public domain, such as cover art or footnotes.

What about fair use?

In limited situations, you can use copyrighted works without permission from the
copyright holder. It can be difficult to figure out whether use of copyrighted
works without permission is legal, though, because the laws in this area are
often vague and vary from country to country.

The copyright law in the United States has a doctrine called "fair use". Fair
use provides a defense to copyright infringement in some circumstances. For
example, fair use allows documentary filmmakers to use very short clips of
copyrighted movies, music and news footage without permission from the copyright
owner. Fair use is a difficult concept because determining whether something is
a fair use involves weighing four factors. Unfortunately, weighing the fair use
factors rarely results in a clear-cut answer.

Rather than applying a fair use test, many other countries have specific
exceptions to copyright infringement. The number and type of exceptions vary by
country, but they frequently allow copyrighted materials to be used without
permission from the copyright holder for activities such as nonprofit research,
teaching, news reporting, or private study.

If you incorrectly decide that something is a fair use or falls into an
exception to copyright infringement, you could be held criminally and civilly
liable and have to pay damages. We suggest you talk to a lawyer if you have
questions regarding fair uses of copyrighted works.

What happens if you upload copyrighted materials to one of our websites without
permission?

By law, we are required to take down videos, music, photographs or other content
you upload onto a website hosted by Microsoft if we learn that it infringes
someone else's copyright. If you believe that we have mistakenly taken down
content you uploaded that you own or have permission to upload, you can also let
us know that. Finally, if you upload infringing content repeatedly, we will
terminate your account and you could face criminal and civil penalties. So
please, respect other people's copyrights.

What if my stuff is on a Microsoft website without my permission?

If you believe that anything on a website hosted by Microsoft infringes your
copyright, let us know. Just provide us with the information requested here and
we will see that your copyrighted works are taken down.

What if I don't want my website crawled?

Microsoft search services (MSN Search and Windows Live Search) follow the Robots
Exclusion Standards. This means that you can control which pages Microsoft
search engines index and how often Microsoft bots access your website. To learn
how to do so, or for more information regarding Microsoft's webcrawling and site
indexing practices, please visit http://search.msn.com/docs/siteowner.aspx.

Top of page
TRADEMARKS.

Trademark information is available at
http://www.microsoft.com/library/toolbar/3.0/trademarks/en-us.mspx.

Any rights not expressly granted herein are reserved.

Send your questions to the appropriate contact as listed below:

    * Microsoft Web properties, contact homepage@microsoft.com.
    * MSN Web properties, contact webmaster@msn.com.
    * Hotmail, contact support@hotmail.com; for spam/privacy issues, contact
abuse@hotmail.com or hotmailprivacy@hotmail.com.
    * Piracy questions can be routed to piracy@microsoft.com or by calling
1-800-R-U-LEGIT begin_of_the_skype_highlighting              1-800-R-U-LEGIT      end_of_the_skype_highlighting.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Microsoft Public License (Ms-PL)
Mon, 2007-10-15 19:23 - nelson

This license governs use of the accompanying software. If you use the software, you
accept this license. If you do not accept the license, do not use the software.

1. Definitions
The terms "reproduce," "reproduction," "derivative works," and "distribution"
have the
same meaning here as under U.S. copyright law.
A "contribution" is the original software, or any additions or changes to the
software.
A "contributor" is any person that distributes its contribution under this license.
"Licensed patents" are a contributor's patent claims that read directly on its
contribution.

2. Grant of Rights
(A) Copyright Grant- Subject to the terms of this license, including the license
conditions and limitations in section 3, each contributor grants you a
non-exclusive, worldwide, royalty-free copyright license to reproduce its
contribution, prepare derivative works of its contribution, and distribute its
contribution or any derivative works that you create.
(B) Patent Grant- Subject to the terms of this license, including the license
conditions and limitations in section 3, each contributor grants you a
non-exclusive, worldwide, royalty-free license under its licensed patents to
make, have made, use, sell, offer for sale, import, and/or otherwise dispose of
its contribution in the software or derivative works of the contribution in the
software.

3. Conditions and Limitations
(A) No Trademark License- This license does not grant you rights to use any
contributors' name, logo, or trademarks.
(B) If you bring a patent claim against any contributor over patents that you
claim are infringed by the software, your patent license from such contributor
to the software ends automatically.
(C) If you distribute any portion of the software, you must retain all
copyright, patent, trademark, and attribution notices that are present in the
software.
(D) If you distribute any portion of the software in source code form, you may
do so only under this license by including a complete copy of this license with
your distribution. If you distribute any portion of the software in compiled or
object code form, you may only do so under a license that complies with this
license.
(E) The software is licensed "as-is." You bear the risk of using it. The
contributors give no express warranties, guarantees or conditions. You may have
additional consumer rights under your local laws which this license cannot
change. To the extent permitted under your local laws, the contributors exclude
the implied warranties of merchantability, fitness for a particular purpose and
non-infringement.
%% The following software may be included in this product:  gst-plugins-base. Use of any of this software is governed by the terms of the license below: 
This module contains LGPL and some executables shipped with the module
contains GPL.  It also has MIT and BSD.  The MIT code is dual licensed
under LGPL.

Link to COPYING file in the gst-plugins-base module:

http://webcvs.freedesktop.org/gstreamer/gst-plugins-base/COPYING?revision=1.1&view=markup

Examples of dual MIT/LGPL license used in the RTSP and SDP plugins:

http://webcvs.freedesktop.org/gstreamer/gst-plugins-base/gst-libs/gst/rtsp/gstrtspmessage.c?revision=1.6&view=markup
http://webcvs.freedesktop.org/gstreamer/gst-plugins-base/gst-libs/gst/sdp/gstsdpmessage.c?view=markup

Example of BSD license used in FFT plugin:

http://webcvs.freedesktop.org/gstreamer/gst-plugins-base/gst-libs/gst/fft/_kiss_fft_guts_s32.h?view=markup

The videoscale plugin has a slightly different variant of the BSD
license:

http://webcvs.freedesktop.org/gstreamer/gst-plugins-base/gst/videoscale/vs_4tap.c?view=markup



For the avoidance of doubt, except that if any license choice other
than GPL or LGPL is available it will apply instead, Oracle elects to
use only the General Public License version 2 (GPLv2) at this time
for any software where a choice of GPL license versions is made
available with the language indicating that GPLv2 or any later
version may be used, or where a choice of which version of the GPL
is applied is otherwise unspecified.

--------------------------------------------------------------------

 Copyright (C) 2001-2002, 2004-2006 Andy Wingo 
 Copyright (C) 2001 Bastien Nocera 
 Copyright (C) 2003-2004 Benjamin Otte 
 Copyright (C) 2001 CodeFactory AB
 Copyright (C) 2002-2003 Colin Walters 
 Copyright (C) 2001-2005, 2007 David Schleef 
 Copyright (C) 2006 Edward Hervey 
 Copyright (C) 1999-2001 Erik Walthinsen 
 Copyright (C) 2006 James Livingston 
 Copyright (C) 2001-2007 Jan Schmidt 
 Copyright (C) 2003, 2005-2006 Julien Moutte 
 Copyright (C) 2006 Kamil Pawlowski 
 Copyright (C) 2002 Kristian Rietveld 
 Copyright (C) 2003 Leif Johnson 
 Copyright (C) 2005 Michael Smith 
 Copyright (C) 2007 Mike Smith 
 Copyright (C) 2005-2006 Nokia  
 Copyright (C) 2007 Rene Stadler 
 Copyright (C) 2001-2005 Ronald Bultje 
 Copyright (C) 2005 Ross Burton 
 Copyright (C) 2006 Ryan Lortie 
 Copyright (C) 2007 Sebastian Dröge 
 Copyright (C) 2005-2006 Stefan Kost 
 Copyright (C) 2002 Steve Baker 
 Copyright (C) 2005-2008 Tim-Philipp Müller 
 Copyright (C) 2007 Thiago Sousa Santos 
 Copyright (C) 2001 Thomas Nyberg 
 Copyright (C) 2001, 2004-2007 Thomas Vander Stichele 
 Copyright (C) 2000, 2004-2007 Wim Taymans 
 Copyright (c) 2006 Young-Ho Cha 

 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Library General Public
 License as published by the Free Software Foundation; either
 version 2 of the License, or (at your option) any later version.

 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 Library General Public License for more details.

 You should have received a copy of the GNU Library General Public
 License along with this library; if not, write to the
 Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 Boston, MA 02111-1307, USA.

--------------------------------------------------------------------

 Copyright (C) 2000-2003 Fabrice Bellard.
 Copyright (C) 2003 Michel Bardiaux for the av_log API
 Copyright (C) 2002-2004 Michael Niedermayer 
 Copyright (C) 2000 Robert Kaye
   
 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public
 License as published by the Free Software Foundation; either
 version 2.1 of the License, or (at your option) any later version.
   
 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 Lesser General Public License for more details.
   
 You should have received a copy of the GNU Lesser General Public
 License along with this library; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

----------------------------------------------------------------------------

 Copyright (C) 1995-1998, 2000-2002 Free Software Foundation, Inc.

 This program is free software; you can redistribute it and/or modify it
 under the terms of the GNU Library General Public License as published
 by the Free Software Foundation; either version 2, or (at your option)
 any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 Library General Public License for more details.

 You should have received a copy of the GNU Library General Public
 License along with this program; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307,
 USA.  

--------------------------------------------------------------------

 Copyright (C) 2006 Daniel Berrange

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

--------------------------------------------------------------------

 Original version Copyright 1988 by The Leland Stanford Junior University
 Copyright 1998 by the University of Washington

 Program:       RFC-822 routines (originally from SMTP)

 Author:        Mark Crispin
                Networks and Distributed Computing
                Computing & Communications
                University of Washington
                Administration Building, AG-44
                Seattle, WA  98195
                Internet: MRC@CAC.Washington.EDU

 Date:  27 July 1988
 Last Edited:   10 September 1998

 Sponsorship:   The original version of this work was developed in the
                Symbolic Systems Resources Group of the Knowledge Systems
                Laboratory at Stanford University in 1987-88, and was funded
                by the Biomedical Research Technology Program of the National
                Institutes of Health under grant number RR-00785.

 Permission to use, copy, modify, and distribute this software and its
 documentation for any purpose and without fee is hereby granted, provided
 that the above copyright notices appear in all copies and that both the
 above copyright notices and this permission notice appear in supporting
 documentation, and that the name of the University of Washington or The
 Leland Stanford Junior University not be used in advertising or publicity
 pertaining to distribution of the software without specific, written prior
 permission.  This software is made available "as is", and
 THE UNIVERSITY OF WASHINGTON AND THE LELAND STANFORD JUNIOR UNIVERSITY
 DISCLAIM ALL WARRANTIES, EXPRESS OR IMPLIED, WITH REGARD TO THIS SOFTWARE,
 INCLUDING WITHOUT LIMITATION ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND
 FITNESS FOR A PARTICULAR PURPOSE, AND IN NO EVENT SHALL THE UNIVERSITY OF
 WASHINGTON OR THE LELAND STANFORD JUNIOR UNIVERSITY BE LIABLE FOR ANY
 SPECIAL, INDIRECT OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER
 RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF
 CONTRACT, TORT (INCLUDING NEGLIGENCE) OR STRICT LIABILITY, ARISING OUT OF
 OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.

--------------------------------------------------------------------

 (PD) 2001 The Bitzi Corporation
 Please see file COPYING or http://bitzi.com/publicdomain 
 for more info.

 NIST Secure Hash Algorithm 
 heavily modified by Uwe Hollerbach  
 from Peter C. Gutmann's implementation as found in 
 Applied Cryptography by Bruce Schneier 
 Further modifications to include the "UNRAVEL" stuff, below 

 This code is in the public domain 

--------------------------------------------------------------------

Copyright (c) 2003-2004, Mark Borgerding
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions
are met:


    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in
      the documentation and/or other materials provided with the
      distribution.
    * Neither the author nor the names of any contributors may be
      used to endorse or promote products derived from this software
      without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.

--------------------------------------------------------------------

 Copyright (c) 2005 David A. Schleef 
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.

 THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT,
 INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 POSSIBILITY OF SUCH DAMAGE.

--------------------------------------------------------------------

 Copyright (C) 2006 Lutz Mueller 
 Copyright (C) 2007 Peter Kjellerstedt  
 Copyright (C) 2005-2007 Wim Taymans 

 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Library General Public
 License as published by the Free Software Foundation; either
 version 2 of the License, or (at your option) any later version.

 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 Library General Public License for more details.

 You should have received a copy of the GNU Library General Public
 License along with this library; if not, write to the
 Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 Boston, MA 02111-1307, USA.

 Unless otherwise indicated, Source Code is licensed under MIT license.
 See further explanation attached in License Statement (distributed in the file
 LICENSE).

 Permission is hereby granted, free of charge, to any person obtaining a copy of
 this software and associated documentation files (the "Software"), to deal in
 the Software without restriction, including without limitation the rights to
 use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 of the Software, and to permit persons to whom the Software is furnished to do
 so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
%% The following software may be included in this product:  gst-plugins-good. Use of any of this software is governed by the terms of the license below: 
This module contains LGPL and some executables shipped with the module
contains GPL.  It also has MIT and BSD.  The MIT code is dual licensed
under LGPL.

Link to COPYING file in the gst-plugins-good module:

http://webcvs.freedesktop.org/gstreamer/gst-plugins-good/COPYING?revision=1.1&view=markup

Example of dual MIT/LGPL licenses from the RTSP and OSXaudio plugins.

http://webcvs.freedesktop.org/gstreamer/gst-plugins-good/gst/rtsp/gstrtsp.c?revision=1.6&view=markup
http://webcvs.freedesktop.org/gstreamer/gst-plugins-good/sys/osxaudio/gstosxaudio.c?view=markup

Example of BSD license from the monoscope plugin.

http://webcvs.freedesktop.org/gstreamer/gst-plugins-good/gst/monoscope/monoscope.c?view=markup

The wavpack plugin contains this "public domain" license:

http://webcvs.freedesktop.org/gstreamer/gst-plugins-good/ext/wavpack/md5.c?revision=1.1&view=markup



For the avoidance of doubt, except that if any license choice other
than GPL or LGPL is available it will apply instead, Oracle elects to
use only the General Public License version 2 (GPLv2) at this time
for any software where a choice of GPL license versions is made
available with the language indicating that GPLv2 or any later
version may be used, or where a choice of which version of the GPL
is applied is otherwise unspecified.

--------------------------------------------------------------------

 Copyright (c) 2001 __CompanyName__. All rights reserved.
 Copyright (C) 2000 Abramo Bagnara 
 Copyright (C) 2005 Alessandro Decina
 Copyright (C) 2003, 2005 Andy Wingo 
 Copyright (C) 2007 Artem Kachitchkine 
 Copyright (C) 2003-2005 Arwed v. Merkatz 
 Copyright (C) 2003-2004 Benjamin Otte 
 Copyright (C) 2005-2007 Brian Cameron 
 Copyright (C) 2006 Christophe Fergeau 
 Copyright (C) 2007 Collabora Ltd,
 Copyright (C) 1998-2005 Conifer Software
 Copyright (C) 2002-2004, 2006-2007 David Schleef 
 Copyright (C) 2000 Daniel Fischer 
 Copyright (C) 2000 Donald A. Graft
 Copyright (C) 2006 Dreamlab Technologies Ltd. 
 Copyright (C) 2002 Ed Tannenbaum
 Copyright (C) 2005-2006 Edgard Lima 
 Copyright (C) 2006-2007 Edward Hervey 
 Copyright (C) 1999-2001 Erik Walthinsen 
 Copyright (C) 1995, 1996, 1997, 1999, 2000 Free Software Foundation, Inc. 
 Copyright (C) 2001 FUKUCHI Kentarou
 Copyright (C) 2002 Iain Holmes 
 Copyright (c) 2001 ios. All rights reserved.
 Copyright (C) 2004-2008 Jan Schmidt 
 Copyright (C) 2006 J\303\274rg Billeter 
 Copyright (C) 2006 Joni Valtanen 
 Copyright (C) 2007 Julien Moutte 
 Copyright (C) 2007 Julien Puydt 
 Copyright (C) 2005 Luca Ognibene 
 Copyright (C) 2006 Mark Nauwelaerts 
 Copyright (C) 2006 Michael Smith 
 Copyright (C) 2005 Michal Benes 
 Copyright (C) 2007 Nokia Corporation
 Copyright (C) 2005 Nokia Corporation 
 Copyright (C) 2006-2007 Nokia Corporation, Stefan Kost .
 Copyright (C) 2008 Nokia Corporation and its subsidary(-ies)
                    contact: 
 Copyright (C) 2005-2006 Philippe Khalaf 
 Copyright (C) 2007 Pioneers of the Inevitable 
 Copyright (C) 1999 Ralph Loader 
 Copyright (C) 2001-2002 Richard Boulton 
 Copyright (C) 2001-2006 Ronald Bultje 
 Copyright (C) 2002-2003 Scott Wheeler  (portions from taglib)
 Copyright (C) 2006-2007 Sebastian Dröge 
 Copyright (C) 2005 Sebastien Moutte 
 Copyright (C) 2006 Sjoerd Simons 
 Copyright (C) 2006-2007 Stefan Kost 
 Copyright (C) 2001 Steve Baker 
 Copyright (C) 2007 Thijs Vermeir 
 Copyright (C) 2000-2005, 2007 Thomas Vander Stichele 
 Copyright (C) 2004-2007 Tim-Philipp Müller 
 Copyright (C) 2000, 2004-2008 Wim Taymans 
 Copyright (C) 2004-2006 Zeeshan Ali 

 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Library General Public
 License as published by the Free Software Foundation; either
 version 2 of the License, or (at your option) any later version.
 
 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 Library General Public License for more details.
 
 You should have received a copy of the GNU Library General Public
 License along with this library; if not, write to the
 Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 Boston, MA 02111-1307, USA.
 
--------------------------------------------------------------------

 Copyright (C) 1995-1998, 2000-2002 Free Software Foundation, Inc.

 This program is free software; you can redistribute it and/or modify it
 under the terms of the GNU Library General Public License as published
 by the Free Software Foundation; either version 2, or (at your option)
 any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 Library General Public License for more details.

 You should have received a copy of the GNU Library General Public
 License along with this program; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307,
 USA.

--------------------------------------------------------------------

 Copyright (C) 1999 Erik Walthinsen 
 Copyright (C) 2001 Sam Mertens.

 This code is subject to the provisions of the GNU Library Public
 License.

--------------------------------------------------------------------

 Copyright (C) 2007-2008 Sebastian Dr\303\266ge 

 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public License
 as published by the Free Software Foundation; either version 2.1 of
 the License, or (at your option) any later version.

 This library is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public
 License along with this library; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 02110-1301 USA

--------------------------------------------------------------------

 Copyright (C) 2006 Daniel Berrange

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

--------------------------------------------------------------------

 This code implements the MD5 message-digest algorithm.
 The algorithm is due to Ron Rivest.    This code was
 written by Colin Plumb in 1993, no copyright is claimed.
 This code is in the public domain; do with it what you wish.

 Equivalent code is available from RSA Data Security, Inc.
 This code has been tested against that, and is equivalent,
 except that you don't need to include two pages of legalese
 with every copy.

 To compute the message digest of a chunk of bytes, declare an
 MD5Context structure, pass it to MD5Init, call MD5Update as
 needed on buffers full of bytes, and then call MD5Final, which
 will fill a supplied 16-byte array with the digest.

 Brutally hacked by John Walker back from ANSI C to K&R (no
   prototypes) to maintain the tradition that Netfone will compile
   with Oracle's original "cc".

--------------------------------------------------------------------

 Copyright (C) 2002 Richard Boulton 
 Copyright (C) 1998-2001 Andy Lo A Foe 
 Original code by Tinic Uro

 This code is copied from Alsaplayer. The orginal code was by Tinic Uro and under
 the BSD license without a advertisig clause. Andy Lo A Foe then relicensed the
 code when he used it for Alsaplayer to GPL with Tinic's permission. Richard Boulton
 then took this code and made a GPL plugin out of it.
 
 7th December 2004 Christian Schaller: Richard Boulton and Andy Lo A Foe gave
 permission to relicense their changes under BSD license so we where able to
restore the 
 code to Tinic's original BSD license.

 This file is under what is known as the BSD license:

 Redistribution and use in source and binary forms, with or without modification, i
 are permitted provided that the following conditions are met:

 1. Redistributions of source code must retain the above copyright notice, this
list of 
 conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright notice,
this list 
 of conditions and the following disclaimer in the documentation and/or other
materials 
 provided with the distribution.
 3. The name of the author may not be used to endorse or promote products
derived from 
 this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR IMPLIED
WARRANTIES, 
 INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
FITNESS FOR 
 A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
ANY DIRECT,
 INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
BUT NOT 
 LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
PROFITS; 
 OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
WHETHER IN 
 CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
IN ANY 
 WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
DAMAGE.

--------------------------------------------------------------------

 Copyright (C) 1999 Erik Walthinsen 
 Copyright (C) 2006 Lutz Mueller 
 Copyright (C) 2007 Pioneers of the Inevitable 
 Copyright (C) 2005-2006 Wim Taymans 
 Copyright (C) 2005-2006 Zaheer Abbas Merali 

 Permission is hereby granted, free of charge, to any person obtaining a
 copy of this software and associated documentation files (the "Software"),
 to deal in the Software without restriction, including without limitation
 the rights to use, copy, modify, merge, publish, distribute, sublicense,
 and/or sell copies of the Software, and to permit persons to whom the
 Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 DEALINGS IN THE SOFTWARE.

 Alternatively, the contents of this file may be used under the
 GNU Lesser General Public License Version 2.1 (the "LGPL"), in
 which case the following provisions apply instead of the ones
 mentioned above:

 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Library General Public
 License as published by the Free Software Foundation; either
 version 2 of the License, or (at your option) any later version.

 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 Library General Public License for more details.

 You should have received a copy of the GNU Library General Public
 License along with this library; if not, write to the
 Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 Boston, MA 02111-1307, USA.

 Unless otherwise indicated, Source Code is licensed under MIT license.
 See further explanation attached in License Statement (distributed in the file
 LICENSE).

 Permission is hereby granted, free of charge, to any person obtaining a copy of
 this software and associated documentation files (the "Software"), to deal in
 the Software without restriction, including without limitation the rights to
 use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 of the Software, and to permit persons to whom the Software is furnished to do
 so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
%% The following software may be included in this product:  gstreamer. Use of any of this software is governed by the terms of the license below: 
GNU LIBRARY GENERAL PUBLIC LICENSE
                       Version 2, June 1991

 Copyright (C) 1991 Free Software Foundation, Inc.
                    675 Mass Ave, Cambridge, MA 02139, USA
 Everyone is permitted to copy and distribute verbatim copies
 of this license document, but changing it is not allowed.

[This is the first released version of the library GPL.  It is
 numbered 2 because it goes with version 2 of the ordinary GPL.]

                            Preamble

  The licenses for most software are designed to take away your
freedom to share and change it.  By contrast, the GNU General Public
Licenses are intended to guarantee your freedom to share and change
free software--to make sure the software is free for all its users.

  This license, the Library General Public License, applies to some
specially designated Free Software Foundation software, and to any
other libraries whose authors decide to use it.  You can use it for
your libraries, too.

  When we speak of free software, we are referring to freedom, not
price.  Our General Public Licenses are designed to make sure that you
have the freedom to distribute copies of free software (and charge for
this service if you wish), that you receive source code or can get it
if you want it, that you can change the software or use pieces of it
in new free programs; and that you know you can do these things.

  To protect your rights, we need to make restrictions that forbid
anyone to deny you these rights or to ask you to surrender the rights.
These restrictions translate to certain responsibilities for you if
you distribute copies of the library, or if you modify it.

  For example, if you distribute copies of the library, whether gratis
or for a fee, you must give the recipients all the rights that we gave
you.  You must make sure that they, too, receive or can get the source
code.  If you link a program with the library, you must provide
complete object files to the recipients so that they can relink them
with the library, after making changes to the library and recompiling
it.  And you must show them these terms so they know their rights.

  Our method of protecting your rights has two steps: (1) copyright
the library, and (2) offer you this license which gives you legal
permission to copy, distribute and/or modify the library.

  Also, for each distributor's protection, we want to make certain
that everyone understands that there is no warranty for this free
library.  If the library is modified by someone else and passed on, we
want its recipients to know that what they have is not the original
version, so that any problems introduced by others will not reflect on
the original authors' reputations.

  Finally, any free program is threatened constantly by software
patents.  We wish to avoid the danger that companies distributing free
software will individually obtain patent licenses, thus in effect
transforming the program into proprietary software.  To prevent this,
we have made it clear that any patent must be licensed for everyone's
free use or not licensed at all.

  Most GNU software, including some libraries, is covered by the ordinary
GNU General Public License, which was designed for utility programs.  This
license, the GNU Library General Public License, applies to certain
designated libraries.  This license is quite different from the ordinary
one; be sure to read it in full, and don't assume that anything in it is
the same as in the ordinary license.

  The reason we have a separate public license for some libraries is that
they blur the distinction we usually make between modifying or adding to a
program and simply using it.  Linking a program with a library, without
changing the library, is in some sense simply using the library, and is
analogous to running a utility program or application program.  However, in
a textual and legal sense, the linked executable is a combined work, a
derivative of the original library, and the ordinary General Public License
treats it as such.

  Because of this blurred distinction, using the ordinary General
Public License for libraries did not effectively promote software
sharing, because most developers did not use the libraries.  We
concluded that weaker conditions might promote sharing better.

  However, unrestricted linking of non-free programs would deprive the
users of those programs of all benefit from the free status of the
libraries themselves.  This Library General Public License is intended to
permit developers of non-free programs to use free libraries, while
preserving your freedom as a user of such programs to change the free
libraries that are incorporated in them.  (We have not seen how to achieve
this as regards changes in header files, but we have achieved it as regards
changes in the actual functions of the Library.)  The hope is that this
will lead to faster development of free libraries.

  The precise terms and conditions for copying, distribution and
modification follow.  Pay close attention to the difference between a
"work based on the library" and a "work that uses the library".  The
former contains code derived from the library, while the latter only
works together with the library.

  Note that it is possible for a library to be covered by the ordinary
General Public License rather than by this special one.

                  GNU LIBRARY GENERAL PUBLIC LICENSE
   TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION

  0. This License Agreement applies to any software library which
contains a notice placed by the copyright holder or other authorized
party saying it may be distributed under the terms of this Library
General Public License (also called "this License").  Each licensee is
addressed as "you".

  A "library" means a collection of software functions and/or data
prepared so as to be conveniently linked with application programs
(which use some of those functions and data) to form executables.

  The "Library", below, refers to any such software library or work
which has been distributed under these terms.  A "work based on the
Library" means either the Library or any derivative work under
copyright law: that is to say, a work containing the Library or a
portion of it, either verbatim or with modifications and/or translated
straightforwardly into another language.  (Hereinafter, translation is
included without limitation in the term "modification".)

  "Source code" for a work means the preferred form of the work for
making modifications to it.  For a library, complete source code means
all the source code for all modules it contains, plus any associated
interface definition files, plus the scripts used to control compilation
and installation of the library.

  Activities other than copying, distribution and modification are not
covered by this License; they are outside its scope.  The act of
running a program using the Library is not restricted, and output from
such a program is covered only if its contents constitute a work based
on the Library (independent of the use of the Library in a tool for
writing it).  Whether that is true depends on what the Library does
and what the program that uses the Library does.
  
  1. You may copy and distribute verbatim copies of the Library's
complete source code as you receive it, in any medium, provided that
you conspicuously and appropriately publish on each copy an
appropriate copyright notice and disclaimer of warranty; keep intact
all the notices that refer to this License and to the absence of any
warranty; and distribute a copy of this License along with the
Library.

  You may charge a fee for the physical act of transferring a copy,
and you may at your option offer warranty protection in exchange for a
fee.

  2. You may modify your copy or copies of the Library or any portion
of it, thus forming a work based on the Library, and copy and
distribute such modifications or work under the terms of Section 1
above, provided that you also meet all of these conditions:

    a) The modified work must itself be a software library.

    b) You must cause the files modified to carry prominent notices
    stating that you changed the files and the date of any change.

    c) You must cause the whole of the work to be licensed at no
    charge to all third parties under the terms of this License.

    d) If a facility in the modified Library refers to a function or a
    table of data to be supplied by an application program that uses
    the facility, other than as an argument passed when the facility
    is invoked, then you must make a good faith effort to ensure that,
    in the event an application does not supply such function or
    table, the facility still operates, and performs whatever part of
    its purpose remains meaningful.

    (For example, a function in a library to compute square roots has
    a purpose that is entirely well-defined independent of the
    application.  Therefore, Subsection 2d requires that any
    application-supplied function or table used by this function must
    be optional: if the application does not supply it, the square
    root function must still compute square roots.)

These requirements apply to the modified work as a whole.  If
identifiable sections of that work are not derived from the Library,
and can be reasonably considered independent and separate works in
themselves, then this License, and its terms, do not apply to those
sections when you distribute them as separate works.  But when you
distribute the same sections as part of a whole which is a work based
on the Library, the distribution of the whole must be on the terms of
this License, whose permissions for other licensees extend to the
entire whole, and thus to each and every part regardless of who wrote
it.

Thus, it is not the intent of this section to claim rights or contest
your rights to work written entirely by you; rather, the intent is to
exercise the right to control the distribution of derivative or
collective works based on the Library.

In addition, mere aggregation of another work not based on the Library
with the Library (or with a work based on the Library) on a volume of
a storage or distribution medium does not bring the other work under
the scope of this License.

  3. You may opt to apply the terms of the ordinary GNU General Public
License instead of this License to a given copy of the Library.  To do
this, you must alter all the notices that refer to this License, so
that they refer to the ordinary GNU General Public License, version 2,
instead of to this License.  (If a newer version than version 2 of the
ordinary GNU General Public License has appeared, then you can specify
that version instead if you wish.)  Do not make any other change in
these notices.

  Once this change is made in a given copy, it is irreversible for
that copy, so the ordinary GNU General Public License applies to all
subsequent copies and derivative works made from that copy.

  This option is useful when you wish to copy part of the code of
the Library into a program that is not a library.

  4. You may copy and distribute the Library (or a portion or
derivative of it, under Section 2) in object code or executable form
under the terms of Sections 1 and 2 above provided that you accompany
it with the complete corresponding machine-readable source code, which
must be distributed under the terms of Sections 1 and 2 above on a
medium customarily used for software interchange.

  If distribution of object code is made by offering access to copy
from a designated place, then offering equivalent access to copy the
source code from the same place satisfies the requirement to
distribute the source code, even though third parties are not
compelled to copy the source along with the object code.

  5. A program that contains no derivative of any portion of the
Library, but is designed to work with the Library by being compiled or
linked with it, is called a "work that uses the Library".  Such a
work, in isolation, is not a derivative work of the Library, and
therefore falls outside the scope of this License.

  However, linking a "work that uses the Library" with the Library
creates an executable that is a derivative of the Library (because it
contains portions of the Library), rather than a "work that uses the
library".  The executable is therefore covered by this License.
Section 6 states terms for distribution of such executables.

  When a "work that uses the Library" uses material from a header file
that is part of the Library, the object code for the work may be a
derivative work of the Library even though the source code is not.
Whether this is true is especially significant if the work can be
linked without the Library, or if the work is itself a library.  The
threshold for this to be true is not precisely defined by law.

  If such an object file uses only numerical parameters, data
structure layouts and accessors, and small macros and small inline
functions (ten lines or less in length), then the use of the object
file is unrestricted, regardless of whether it is legally a derivative
work.  (Executables containing this object code plus portions of the
Library will still fall under Section 6.)

  Otherwise, if the work is a derivative of the Library, you may
distribute the object code for the work under the terms of Section 6.
Any executables containing that work also fall under Section 6,
whether or not they are linked directly with the Library itself.

  6. As an exception to the Sections above, you may also compile or
link a "work that uses the Library" with the Library to produce a
work containing portions of the Library, and distribute that work
under terms of your choice, provided that the terms permit
modification of the work for the customer's own use and reverse
engineering for debugging such modifications.

  You must give prominent notice with each copy of the work that the
Library is used in it and that the Library and its use are covered by
this License.  You must supply a copy of this License.  If the work
during execution displays copyright notices, you must include the
copyright notice for the Library among them, as well as a reference
directing the user to the copy of this License.  Also, you must do one
of these things:

    a) Accompany the work with the complete corresponding
    machine-readable source code for the Library including whatever
    changes were used in the work (which must be distributed under
    Sections 1 and 2 above); and, if the work is an executable linked
    with the Library, with the complete machine-readable "work that
    uses the Library", as object code and/or source code, so that the
    user can modify the Library and then relink to produce a modified
    executable containing the modified Library.  (It is understood
    that the user who changes the contents of definitions files in the
    Library will not necessarily be able to recompile the application
    to use the modified definitions.)

    b) Accompany the work with a written offer, valid for at
    least three years, to give the same user the materials
    specified in Subsection 6a, above, for a charge no more
    than the cost of performing this distribution.

    c) If distribution of the work is made by offering access to copy
    from a designated place, offer equivalent access to copy the above
    specified materials from the same place.

    d) Verify that the user has already received a copy of these
    materials or that you have already sent this user a copy.

  For an executable, the required form of the "work that uses the
Library" must include any data and utility programs needed for
reproducing the executable from it.  However, as a special exception,
the source code distributed need not include anything that is normally
distributed (in either source or binary form) with the major
components (compiler, kernel, and so on) of the operating system on
which the executable runs, unless that component itself accompanies
the executable.

  It may happen that this requirement contradicts the license
restrictions of other proprietary libraries that do not normally
accompany the operating system.  Such a contradiction means you cannot
use both them and the Library together in an executable that you
distribute.

  7. You may place library facilities that are a work based on the
Library side-by-side in a single library together with other library
facilities not covered by this License, and distribute such a combined
library, provided that the separate distribution of the work based on
the Library and of the other library facilities is otherwise
permitted, and provided that you do these two things:

    a) Accompany the combined library with a copy of the same work
    based on the Library, uncombined with any other library
    facilities.  This must be distributed under the terms of the
    Sections above.

    b) Give prominent notice with the combined library of the fact
    that part of it is a work based on the Library, and explaining
    where to find the accompanying uncombined form of the same work.

  8. You may not copy, modify, sublicense, link with, or distribute
the Library except as expressly provided under this License.  Any
attempt otherwise to copy, modify, sublicense, link with, or
distribute the Library is void, and will automatically terminate your
rights under this License.  However, parties who have received copies,
or rights, from you under this License will not have their licenses
terminated so long as such parties remain in full compliance.

  9. You are not required to accept this License, since you have not
signed it.  However, nothing else grants you permission to modify or
distribute the Library or its derivative works.  These actions are
prohibited by law if you do not accept this License.  Therefore, by
modifying or distributing the Library (or any work based on the
Library), you indicate your acceptance of this License to do so, and
all its terms and conditions for copying, distributing or modifying
the Library or works based on it.

  10. Each time you redistribute the Library (or any work based on the
Library), the recipient automatically receives a license from the
original licensor to copy, distribute, link with or modify the Library
subject to these terms and conditions.  You may not impose any further
restrictions on the recipients' exercise of the rights granted herein.
You are not responsible for enforcing compliance by third parties to
this License.

  11. If, as a consequence of a court judgment or allegation of patent
infringement or for any other reason (not limited to patent issues),
conditions are imposed on you (whether by court order, agreement or
otherwise) that contradict the conditions of this License, they do not
excuse you from the conditions of this License.  If you cannot
distribute so as to satisfy simultaneously your obligations under this
License and any other pertinent obligations, then as a consequence you
may not distribute the Library at all.  For example, if a patent
license would not permit royalty-free redistribution of the Library by
all those who receive copies directly or indirectly through you, then
the only way you could satisfy both it and this License would be to
refrain entirely from distribution of the Library.

If any portion of this section is held invalid or unenforceable under any
particular circumstance, the balance of the section is intended to apply,
and the section as a whole is intended to apply in other circumstances.

It is not the purpose of this section to induce you to infringe any
patents or other property right claims or to contest validity of any
such claims; this section has the sole purpose of protecting the
integrity of the free software distribution system which is
implemented by public license practices.  Many people have made
generous contributions to the wide range of software distributed
through that system in reliance on consistent application of that
system; it is up to the author/donor to decide if he or she is willing
to distribute software through any other system and a licensee cannot
impose that choice.

This section is intended to make thoroughly clear what is believed to
be a consequence of the rest of this License.

  12. If the distribution and/or use of the Library is restricted in
certain countries either by patents or by copyrighted interfaces, the
original copyright holder who places the Library under this License may add
an explicit geographical distribution limitation excluding those countries,
so that distribution is permitted only in or among countries not thus
excluded.  In such case, this License incorporates the limitation as if
written in the body of this License.

  13. The Free Software Foundation may publish revised and/or new
versions of the Library General Public License from time to time.
Such new versions will be similar in spirit to the present version,
but may differ in detail to address new problems or concerns.

Each version is given a distinguishing version number.  If the Library
specifies a version number of this License which applies to it and
"any later version", you have the option of following the terms and
conditions either of that version or of any later version published by
the Free Software Foundation.  If the Library does not specify a
license version number, you may choose any version ever published by
the Free Software Foundation.

  14. If you wish to incorporate parts of the Library into other free
programs whose distribution conditions are incompatible with these,
write to the author to ask for permission.  For software which is
copyrighted by the Free Software Foundation, write to the Free
Software Foundation; we sometimes make exceptions for this.  Our
decision will be guided by the two goals of preserving the free status
of all derivatives of our free software and of promoting the sharing
and reuse of software generally.

                            NO WARRANTY

  15. BECAUSE THE LIBRARY IS LICENSED FREE OF CHARGE, THERE IS NO
WARRANTY FOR THE LIBRARY, TO THE EXTENT PERMITTED BY APPLICABLE LAW.
EXCEPT WHEN OTHERWISE STATED IN WRITING THE COPYRIGHT HOLDERS AND/OR
OTHER PARTIES PROVIDE THE LIBRARY "AS IS" WITHOUT WARRANTY OF ANY
KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
PURPOSE.  THE ENTIRE RISK AS TO THE QUALITY AND PERFORMANCE OF THE
LIBRARY IS WITH YOU.  SHOULD THE LIBRARY PROVE DEFECTIVE, YOU ASSUME
THE COST OF ALL NECESSARY SERVICING, REPAIR OR CORRECTION.

  16. IN NO EVENT UNLESS REQUIRED BY APPLICABLE LAW OR AGREED TO IN
WRITING WILL ANY COPYRIGHT HOLDER, OR ANY OTHER PARTY WHO MAY MODIFY
AND/OR REDISTRIBUTE THE LIBRARY AS PERMITTED ABOVE, BE LIABLE TO YOU
FOR DAMAGES, INCLUDING ANY GENERAL, SPECIAL, INCIDENTAL OR
CONSEQUENTIAL DAMAGES ARISING OUT OF THE USE OR INABILITY TO USE THE
LIBRARY (INCLUDING BUT NOT LIMITED TO LOSS OF DATA OR DATA BEING
RENDERED INACCURATE OR LOSSES SUSTAINED BY YOU OR THIRD PARTIES OR A
FAILURE OF THE LIBRARY TO OPERATE WITH ANY OTHER SOFTWARE), EVEN IF
SUCH HOLDER OR OTHER PARTY HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH
DAMAGES.

                     END OF TERMS AND CONDITIONS

     Appendix: How to Apply These Terms to Your New Libraries

  If you develop a new library, and you want it to be of the greatest
possible use to the public, we recommend making it free software that
everyone can redistribute and change.  You can do so by permitting
redistribution under these terms (or, alternatively, under the terms of the
ordinary General Public License).

  To apply these terms, attach the following notices to the library.  It is
safest to attach them to the start of each source file to most effectively
convey the exclusion of warranty; and each file should have at least the
"copyright" line and a pointer to where the full notice is found.

    
    Copyright (C)   

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Library General Public
    License as published by the Free Software Foundation; either
    version 2 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Library General Public License for more details.

    You should have received a copy of the GNU Library General Public
    License along with this library; if not, write to the Free
    Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.

Also add information on how to contact you by electronic and paper mail.

You should also get your employer (if you work as a programmer) or your
school, if any, to sign a "copyright disclaimer" for the library, if
necessary.  Here is a sample; alter the names:

  Yoyodyne, Inc., hereby disclaims all copyright interest in the
  library `Frob' (a library for tweaking knobs) written by James Random Hacker.

  , 1 April 1990
  Ty Coon, President of Vice

That's all there is to it!
%% The following software may be included in this product:  sg_player. Use of any of this software is governed by the terms of the license below: 
The attached license is in reference to the files listed below from the Intel® 
Media Processor CE3100 SDK:

For JMF Plugin:

    display_config.c
    display_config.h
    gst_pipe.c
    gst_pipe.h
    GstPlayer.cpp
    psi_handling.c
    psi_handling.h
    psi_handling_wrapper.c
    psi_handling_wrapper.h
    read_configfile.c
    read_configfile.h
    README_sg_player.txt
    sg_player
    sg_player.c
    sg_player.h

and

For JAVAFX JOGL Component:

    gdl.h

Intel hereby grants Oracle America the right to distribute the Intel code 
described above under the terms of the Intel Media Processor Software 
Development Kit (SDK) license agreement.  Notwithstanding the terms of  Intel 
Media Processor Software Development Kit (SDK) license agreement, Intel hereby 
grants Oracle America the right to publicly display, demonstrate, and 
distribute the Object Code of Derived Code and Licensed Software for Oracle's 
support of Intel-based consumer electronics platforms. Intel further grants to 
Oracle the right to disclose the Object Code of Derived Code to third parties who 
participate in the testing, evaluation and demonstration of the Oracle JMF and 
JOGL technology in connection with Oracle's support of Intel-based consumer 
electronics platforms.  Intel acknowledges and agrees that the Licensed 
Software may be hosted for testing, evaluation, and demonstration purposes at 
a third party's location, and that Oracle has the right to provide the Licensed 
Software to these third parties.  For purposes of this permission, the 
Licensed Software is Distributable as Sample Source Code and may be 
distributed in Object Code form (except for gdl.h, which may be delivered in 
the form customary provided for header files).

Please acknowledge acceptance of these terms in a return email.

 

Regards,

 

Jeffery Noah

Digital Home Group, CE Platform AE
Intel Corp.
+1 503 712 8384 begin_of_the_skype_highlighting              +1 503 712 8384      end_of_the_skype_highlighting
+1 503 799 7045 begin_of_the_skype_highlighting              +1 503 799 7045      end_of_the_skype_highlighting cell


INTEL SOFTWARE LICENSE AGREEMENT
                      Intel Media Processor Software Development Kit (SDK)
              (Internal Use, Object Code Distribution and Sample Source Code
Distribution)
              IMPORTANT - READ BEFORE COPYING, INSTALLING OR USING.
DO NOT USE OR LOAD THIS INTEL SOFTWARE UNTIL YOU HAVE CAREFULLY
READ THE FOLLOWING TERMS AND CONDITIONS. BY LOADING OR USING
THIS INTEL SOFTWARE, YOU ("YOU" OR "LICENSEE") AGREE TO THE TERMS
OF THIS INTEL SOFTWARE LICENSE AGREEMENT (THIS "AGREEMENT"). IF
YOU DO NOT WISH TO SO AGREE, DO NOT COPY, INSTALL OR USE THIS INTEL
SOFTWARE. IF YOU ARE AN AGENT OR EMPLOYEE OF A LEGAL ENTITY YOU
REPRESENT AND WARRANT THAT YOU HAVE THE AUTHORITY TO BIND SUCH
LEGAL ENTITY TO THIS AGREEMENT.
Portions of the Intel Software within this SDK may be Pre-Production Licensed
Software as defined below. The Pre-Production Licensed Software sections of
this SDK may not have been fully validated by Intel or be feature-complete. 
Intel
may make updates available to You of production versions which may have gone
through Intel's software validation and quality control efforts. YOU ARE
RESPONSIBLE FOR YOUR DECISION TO USE PRE-PRODUCTION LICENSED
SOFTWARE IN YOUR COMMERCIAL PRODUCT.
1. DEFINED TERMS
     1.1 "Confidential Information" means the Licensed Software provided in
Source Code form, and the Licensed
Documentation that is not End User Documentation.
     1.2 "Derived Code" means derivative work, as defined by 17 USC Section 
101,
of the Licensed Software
provided by Intel to Licensee in Source Code form.
     1.3 "Distributor" means a third-party, including any of Licensee's
subsidiaries or affiliates, that is authorized by
Licensee to distribute, Sell, or Offer Products for Sale.
     1.4 "End User" means the final or ultimate user of a Product that uses a
Product for its own purposes and not for
further distribution or resale.
     1.5 "End User Documentation" means Licensed Documentation that may be
included with the Licensed
Software.
     1.6 "Independent Software Vendor" or "ISV" means an individual or entity
that makes or produces software that
incorporates Licensed Software.
     1.7 "Intel" means Intel Corporation and its wholly owned subsidiaries and
affiliates as the case may be.
     1.8 "Intel Party" or "Intel Parties" means Intel and its officers,
directors, employees, affiliates, sub-contractors,
subsidiaries and suppliers (and any respective officer, director, employee,
affiliate, sub-contractor or subsidiary
thereof).
     1.9 "Internal Use Software" means the Licensed Software identified as 
Intel
Confidential, or other confidential
markings in the File Header.
     1.10 "Licensed Documentation" means installation and user guides, and
manuals and other documentation in
either printed or machine-viewable form documentation corresponding to the
Licensed Software provided by Intel in
connection with, under, or subject to this Agreement.
10/09
      1.11 "Licensed Patent Claims" means those claims of Intel's patents that
(a) are infringed by the Licensed
Software (as delivered by Intel in its unmodified form) and (b) Intel has the
right to license.
      1.12 "Licensed Software" means any computing programming code, including
Pre-Production Licensed
Software, that is provided in connection with, under, or subject to this
Agreement, but does not include computing
programming code that is (i) delivered with the Licensed Software that is Open
Source Software and/or (ii) subject to
an agreement, obligation and/or license (in each case whether or not
accompanying such computing programming
code) intended to supersede or override this Agreement. The file directories,
sub-directories and individual File
Headers included with the Licensed Software shall contain information to
identify the Licensed Software and any
Open Source Software.
      1.13 "Licensee" or "Licensee's" means Licensee as an individual or, if
Licensee are accepting these terms on
behalf of a legal entity (such as a corporation, partnership or government
agency), "Licensee", "Licensee's", "You" or
"Your" means such legal entity.
      1.14 "File Header(s)" means the information provided with the Licensed
Software and any Open Source
Software that identifies (i) Internal Use Software, Distributable As Object
Software, Distributable As Sample Source
Software, or Open Source Software, and (ii) any notices or additional
information about the Licensed Software or
Open Source Software.
      1.15 "Object Code" means computer programming code in binary form 
suitable
for machine execution by a
processor without the intervening steps of interpretation or compilation.
      1.16 "Open Source Software" means any software that requires as a
condition of use, modification and/or
distribution of such software that such software or other software incorporated
into, derived from or distributed with
such software (a) be disclosed or distributed in Source Code form; (b) be
licensed by the user to third parties for the
purpose of making and/or distributing derivative works; or (c) be
redistributable at no charge. Open Source Software
includes, without limitation, software licensed or distributed under any of the
following licenses or distribution models,
or licenses or distribution models substantially similar to any of the
following: (a) GNU's General Public License (GPL)
or Lesser/Library GPL (LGPL), (b) the Artistic License (e.g., PERL), (c) the
Mozilla Public License, (d) the Netscape
Public License, (e) the Oracle Community Source License (SCSL), (f) the Oracle
Industry Source License (SISL), and (g)
the Common Public License (CPL).
      1.17 "Original Equipment Manufacturer" or "OEM" means an individual or
entity that makes or produce
hardware products that incorporates Licensed Software.
      1.18 "Pre-Production Licensed Software" means the pre-production versions
or features of Licensed Software
that have not been validated nor passed Intel's software quality controls. The
release notes included or provided with
the PDK shall contain information to help identify the Pre-Production Licensed
Software.
      1.19 "Product" means a consumer electronics hardware product designed and
manufactured by You, or solely
for You, to Your specifications and sold under Your brand or trade name that
includes the Intel® Media Processor CE
3100 or Intel® AtomTM processor CE4100.
      1.20 "Distributable As Object Software" means the Licensed Software
provided in Object Code form that is not
identified as Internal Use Software.
      1.21 "Distributable As Sample Source Software" means the Licensed 
Software
in Source Code form that is
as identified as "Distributable As Sample Source Software" in the File Header
included with the Licensed Software.
      1.22 "Sell" and "Offer for Sale" mean, respectively, for the purposes of
the patent licenses granted under
Section 2, to sublicense, or offer to sublicense, the Licensed Software, as 
part
of a sale, or offer to sell, the Licensee
Products, and "Sale" and "Offer to Sell", in no way mean to transfer ownership
rights in the Licensed Software.
      1.23 "Source Code" means computer programming code in an uncompiled form
readable by humans which
cannot be executed by a processor unless it is complied into Object Code.
2. LICENSE GRANTS AND RESTRICTIONS
      2.1 Licenses to Internal Use Software. Intel grants to Licensee the
following non-transferable, non-exclusive,
worldwide, royalty-free, perpetual (unless terminated in accordance with the
Section 9 ("Termination") below), non-
sublicensable licenses:
           2.1.1 Internal Use License. A license (i) under Intel's copyrights 
to
reproduce and (ii) under Intel's Licensed
Patent Claims to use, a reasonable number of copies the Internal Use Software,
alone or to the extent the Internal
Use Software is incorporated into Derived Code of the Internal Use Software,
internally within Licensee's facilities
solely and exclusively for the purpose of manufacture and/or internal
development of Products; and
10/09
           2.1.2 License to Create Derivative Works. A license under Intel's
copyrights to create Derived Code of the
Internal Use Software provided in Sample Source Code form, internally within
Licensee's facilities and solely and
exclusively for the purpose of manufacture and/or internal development of 
Products.
      2.2 Licenses to Distributable As Object Software. Intel grants to 
Licensee
the following non-transferable, non-
exclusive, worldwide, royalty-free, perpetual (unless terminated in accordance
with the Section 9 ("Termination")
below), non-sublicensable (except pursuant to Section 2.2.4 below) licenses:
           2.2.1 Internal Use License. A license (i) under Intel's copyrights 
to
reproduce and (ii) under Intel's Licensed
Patent Claims to use, a reasonable number of copies the Distributable As Object
Software, alone or to the extent the
Distributable as Object Software is incorporated into Derived Code of the
Distributable As Object Software, internally
within Licensee's facilities solely and exclusively for the purpose of
manufacture and/or internal development of
Products;
           2.2.2 License to Create Derivative Works. A license under Intel's
copyrights to create Derived Code of the
Distributable As Object Software provided in Sample Source Code form, 
internally
within Licensee's facilities and
solely and exclusively for the purpose of manufacture and/or internal
development of Products; and
           2.2.3 Object Code Distribution License. A license (i) under Intel's
copyrights to distribute and (ii) under
Intel's Licensed Patent Claims to Sell, Offer for Sale, make, and import, the
Distributable As Object Software, in
Object Code form, alone or to the extent the Distributable as Object Software 
is
incorporated into Derived Code of
Distributable As Object Software, solely as part of and within Products.
           2.2.4 Object Code Distribution Sublicense.
                     2.2.4.1 OEMs. Intel grants to a Licensee acting as an OEM
the right to sublicense (subject to the
minimum end user license terms set forth in Exhibit A) to End Users through one
or more tiers of Distributors, the
right to execute the Distributable As Object Software, alone or to the extent
the Distributable as Object Software is
incorporated into Derived Code, solely within or as part of a Product.
                     2.2.4.2 ISVs. Intel grants to a Licensee acting as an ISV
(i) the right to sublicense (subject to the
minimum end user license terms set forth in Exhibit A) to End Users through one
or more tiers of Distributors, the
right to execute the Distributable As Object Software, alone or to the extent
the Distributable as Object Software is
incorporated into Derived Code, solely within or as part of a Product and (ii)
the right to sublicense to OEMs the
Object Code form distribution license set forth in Section 2.2.3 subject to the
restriction in Section 2.2.4.1.
      2.3 Licenses to Distributable As Sample Source Software. Intel grants to
Licensee the following non-
transferable, non-exclusive, worldwide, royalty-free, perpetual (unless
terminated in accordance with the Section 9
("Termination") below), non-sublicensable (except pursuant to Section 2.5,
below) licenses
           2.3.1 License to Create Derivative Works. A license under Intel's
copyrights to create Derived Code of the
Distributable As Sample Source Software provided in Source Code form, 
internally
within Licensee's facilities and
solely and exclusively for the purpose of manufacture and/or internal
development of Products;
           2.3.2 Internal Use License. A license (i) under Intel's copyrights 
to
reproduce and (ii) under Intel's Licensed
Patent Claims to use, a reasonable number of copies the Distributable As Sample
Source Software, alone or to the
extent the Distributable as Sample Source Software is incorporated into Derived
Code of the Distributable As Sample
Source Software, internally within Licensee's facilities solely and exclusively
for the purpose of manufacture and/or
internal development of Products;
           2.3.3 Object Code Distribution License. A license (i) under Intel's
copyrights to distribute and (ii) under
Intel's Licensed Patent Claims to Sell, Offer for Sale, make and import, the
Distributable As Sample Source Software,
in Object Code form, alone or to the extent the Distributable as Sample Source
Software is incorporated into Derived
Code of Distributable As Sample Source Software, solely as part of and within
Products; and
           2.3.4 Source Code Distribution License. A license (i) under Intel's
copyrights to distribute and (ii) under
Intel's Licensed Patent Claims to Sell, Offer for Sale, and import, the
Distributable As Sample Source Software, in
Source Code form, alone or to the extent the Distributable as Sample Source
Software is incorporated into Derived
Code of Distributable As Sample Source Software, to OEMs, solely as part of and
within Products.
           2.3.5 Object and Source Code Distribution Sublicense.
                     2.3.5.1 OEMs. Intel grants to a Licensee acting as an OEM
the right to sublicense (subject to the
minimum end user license terms set forth in Exhibit A) to End Users through one
or more tiers of Distributors, the
right to execute the Distributable As Sample Source Software in Object Code
form, alone or to the extent the
Distributable as Sample Source Software is incorporated into Derived Code,
solely within or as part of a Product.
10/09
                     2.3.5.2 ISVs. Intel grants to a Licensee acting as an ISV
(i) the right to sublicense (subject to the
minimum end user license terms set forth in Exhibit A) to End Users through one
or more tiers of Distributors, the
right to execute the Distributable As Source Software in Object Code form, 
alone
or to the extent the Distributable as
Sample Source Software is incorporated into Derived Code, solely within or as
part of a Product; (ii) the right to
sublicense to OEMs the Object Code form distribution license set forth in
section 2.3.3 subject to the restriction in
Section 2.3.5.1 and (iii) the right to sublicense to OEMs the license to create
Derived Code of the Distributable As
Source Software set forth in Section 2.3.4 subject to the restriction in 
Section
2.3.5.1.
      2.4 Licenses to Documentation. Intel grants to Licensee the following
non-transferable, non-exclusive,
worldwide, royalty-free, perpetual (unless terminated in accordance with the
Section 9 ("Termination") below), non-
sublicensable licenses
           2.4.1 Internal Use License. A license under Intel's copyrights to
reproduce a reasonable number of copies
of the Licensed Documentation internally within Licensee's facilities solely 
and
exclusively for the purpose of
manufacture and/or internal development of Products; and
           2.4.2 End User Documentation Distribution License. A license under
Intel's copyrights to distribute End
User Documentation to End Users through one or more tiers of Distributors 
solely
with Products.
      2.5 Restrictions. Neither Licensee, nor any OEM, or Distributor, shall:
           2.5.1 Have any right or license to reverse engineer, decompile or
disassemble any Licensed Software not
provided in Source Code form, except in jurisdictions where licenses for such
activities cannot be withheld;
           2.5.2 Grant to any third-party, or encourage any third-party to
exercise, any rights broader than those rights
Intel has granted to Licensee under this Agreement, and any such attempt is 
null
and void;
           2.5.3 Alter, remove or obscure any proprietary notices from the
Licensed Software relating to Intel's or its
vendors' intellectual property rights;
           2.5.4 Make any statement that any Products are endorsed by, 
certified
by or that its performance is
guaranteed by Intel or use Intel's name or trademarks to market any Product
without express prior written permission
from Intel;
           2.5.5 Imply or otherwise indicate that Licensee is selling or
transferring title to any Licensed Software; and
           2.5.6 Grant an End User any right to sublicense the Licensed 
Software
to any party.
      2.6 Written Agreements. All agreements or licenses between Licensee and
any third party with respect to the
Licensed Software must be in writing and at least as protective of Intel's
rights in the Licensed Software as this
Agreement. For Licensee's convenience, any written agreement referenced in this
Section 2.6 with an OEM,
Distributor or End User may also pertain to other computer programming code
without specifically referencing the
Licensed Software (as long as it covers the Licensed Software), but Intel 
grants
no right hereunder with respect to
such other computer programming code.
      2.7 Open Source Software. In the event Open Source Software is included
with Licensed Software, such Open
Source Software is licensed pursuant to the applicable Open Source Software
license agreement identified in the
Open Source Software comments in the applicable source code file(s) and/or File
Header provided with Licensed
Software. Additional detail may be provided (where applicable) in the
accompanying on-line documentation. With
respect to the Open Source Software, nothing in this Agreement limits any 
rights
under, or grants rights that
supersede, the terms of any applicable Open Source Software license agreement.
Neither Licensee nor any OEM or
Distributor, shall subject the Licensed Software or Licensed Documentation, in
whole or in part, to any license
obligations associated with Open Source Software including combining or
distributing the Licensed Software and/or
Documentation with Open Source Software in a manner that subjects Intel or any
portion of the Licensed Software
provided by Intel hereunder to any license obligations of such Open Source 
Software.
      2.8 Additional Terms For Windows Media Enabled Versions. This product
includes technology owned by
Microsoft Corporation and cannot be used or further distributed by Licensee
without a license from Microsoft or a
Microsoft Affiliate. If you have questions on whether you received these
components, please contact your Intel
representative.
      2.9 Media Player Sample Source Code. The Licensed Software may contain
sample application source code
for the XPSM media player. You can use the Licensed Software and any 
application
sample source code provided
solely in connection with creating media playback functionality in your 
Product,
but you shall not use the application
sample source code, or any other portions of the Licensed Software, to design,
produce, sell or otherwise transfer or
distribute Products or software, where such Products or software are designed 
to
circumvent the requirements or
effectiveness of any content protection or digital rights management
technologies (or assist others directly or
indirectly to do any of the foregoing).
10/09
3. License to Use Comments and Suggestions. This Agreement does NOT obligate
Licensee to provide Intel with
comments or suggestions regarding the Licensed Software. However, should
Licensee provide Intel with comments
or suggestions for the modification, correction, improvement or enhancement of
(a) the Licensed Software or (b) Intel
products or processes which work with the Licensed Software, Licensee grants to
Intel a non-exclusive, irrevocable,
worldwide, royalty-free license, with the right to sublicense Intel's licensees
and customers, under Licensee's
intellectual property rights, the rights to use and disclose such comments and
suggestions in any manner Intel
chooses and to display, perform, copy, make, have made, use, sell, and 
otherwise
dispose of Intel's and its
sublicensee's products embodying such comments and suggestions in any manner 
and
via any media Intel chooses,
without reference to the source.
4. OWNERSHIP, NO OTHER RIGHTS.
      4.1 The Licensed Software is protected by the intellectual property laws
of the United States and other countries,
and international treaty provisions. Licensee and Intel agree that except as
otherwise expressly provided in Section 2
("License Grants") above, no license or other right is granted by Intel to
Licensee by implication, estoppel or
otherwise, under Intel patents, copyrights, trademarks, or other intellectual
property rights. All rights not expressly
granted to Licensee pursuant to Section 2 of this Agreement are hereby 
expressly
reserved for Intel and/or the Intel
Parties and are expressly excluded from this Agreement.
      4.2 Title to all copies of the Licensed Software and any intellectual
property therein remains with Intel and/or the
Intel Parties. Licensee may not disclose the terms of this Agreement or use
Intel's name in any publications,
advertisements, or other announcements without Intel's prior written consent.
This Agreement does not grant
Licensee any rights to use Intel's trademarks.
      4.3 Licensee acknowledges and agrees that Licensee's use of the Licensed
Software or distribution thereof with
Products as permitted by this Agreement may require Licensee to procure
license(s) from one or more third parties
that may hold intellectual property rights applicable to the media format,
decoding, encoding, transcoding, digital
rights management capabilities and/or content protection technologies of the
Licensed Software, if any.
5. CONFIDENTIALITY
      5.1 Confidentiality. Confidential Information disclosed under this
Agreement is governed by Licensee's
Corporate Non Disclosure Agreement ("CNDA") with Intel (if any). If Licensee 
and
Intel do not have a CNDA, then the
provisions of Section 5.2 shall apply to Confidential Information disclosed
under this Agreement.
      5.2 Obligations of Receiving Party. Licensee will maintain the
confidentiality of the Confidential Information of
Intel with at least the same degree of care that it uses to protect its own
confidential and proprietary information, but
no less than a reasonable degree of care under the circumstances. Licensee will
not disclose any Confidential
Information to any employees or to any third parties except to the Licensee's
employees, parent company and
majority-owned subsidiaries, and, in the case of Distributable Sample Source
Software, OEMs who have a need to
know and who agree to abide by nondisclosure terms at least as comprehensive as
those set forth herein; provided
that the Licensee will be liable for breach by any such entity. For the 
purposes
of this Agreement, the term
"employees" shall include independent contractors of Licensee, who have agreed
in writing to Licensee that they are
subject to the terms of this Agreement, and they retain no license rights or
other rights hereunder once they have
discontinued their performance of work on Licensee's behalf. Licensee, however,
will not be liable for the disclosure
of any Confidential Information which is:
      a.   rightfully in the public domain other than by a breach of a duty to
Intel;
      b.   rightfully received from a third party without any obligation of
confidentiality;
      c.   rightfully known to the Licensee without any limitation on use or
disclosure prior to its receipt from Intel;
      d.   independently developed by employees of Licensee; or
      e.   generally made available to third parties by Intel without
restriction on disclosure.
Title or the right to possess Confidential Information as between the parties
will remain with Intel.
6. EXCLUSION OF WARRANTIES. THE LICENSED SOFTWARE IS PROVIDED "AS-IS", WITHOUT
WARRANTY
OF ANY KIND AND POSSIBLY WITH DEFECTS. INTEL AND THE INTEL PARTIES MAKE NO
WARRANTIES,
EITHER EXPRESS OR IMPLIED, WITH RESPECT TO THE LICENSED SOFTWARE. INTEL AND THE
INTEL
PARTIES SPECIFICALLY DISCLAIM THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
FITNESS FOR A
PARTICULAR PURPOSE AND ANY WARRANTY AGAINST INFRINGEMENT OF ANY INTELLECTUAL
PROPERTY RIGHT OF ANY THIRD PARTY.
7. SUPPORT. Intel may make changes to the Licensed Software, or to items
referenced therein, at any time without
notice, but is not obligated to support, update or provide training for the
Licensed Software. Intel may in its sole
10/09
discretion offer such services under separate terms at Intel's then-current
rates. Licensee may request additional
information on Intel's service offerings from an Intel sales representative.
Licensee agrees to be solely responsible to
Licensee's OEMs, Distributors and End Users for any update or support 
obligation
or other liability which may arise
from the distribution of a Product.
8. LIMITATION OF LIABILITY.
      8.1 IN NO EVENT SHALL INTEL OR THE INTEL PARTIES BE LIABLE FOR ANY 
DAMAGES
WHATSOEVER
(INCLUDING, WITHOUT LIMITATION, LOST PROFITS, LOST DATA, LOSS OF GOODWILL, 
BUSINESS
INTERRUPTION, OR LOST INFORMATION) ARISING OUT OF THE USE OF OR INABILITY TO 
USE THE
LICENSED SOFTWARE, EVEN IF AN INTEL PARTY HAS BEEN ADVISED OF THE POSSIBILITY 
OF
SUCH
DAMAGES. SOME JURISDICTIONS PROHIBIT EXCLUSION OR LIMITATION OF LIABILITY FOR
IMPLIED
WARRANTIES OR CONSEQUENTIAL OR INCIDENTAL DAMAGES, SO THE ABOVE LIMITATION MAY 
NOT
APPLY TO LICENSEE. LICENSEE MAY ALSO HAVE OTHER LEGAL RIGHTS THAT VARY FROM
JURISDICTION
TO JURISDICTION.
      8.2 THE LICENSED SOFTWARE LICENSED HEREUNDER IS NOT DESIGNED OR INTENDED
FOR USE IN
ANY MEDICAL, LIFE SAVING OR LIFE SUSTAINING SYSTEMS, TRANSPORTATION SYSTEMS, 
NUCLEAR
SYSTEMS, OR FOR ANY OTHER MISSION CRITICAL APPLICATION IN WHICH THE FAILURE OF 
THE
LICENSED SOFTWARE COULD LEAD TO PERSONAL INJURY OR DEATH. LICENSEE SHALL
INDEMNIFY AND
HOLD INTEL AND THE INTEL PARTIES HARMLESS AGAINST ALL CLAIMS, COSTS, DAMAGES, 
AND
EXPENSES, AND REASONABLE ATTORNEY FEES ARISING OUT OF, DIRECTLY OR INDIRECTLY,
THE USE
AND DISTRIBUTION OF THE LICENSED SOFTWARE BY LICENSEE, OEMs, DISTRIBUTORS, END
USERS, OR
ANY THIRD PARTY, AND ANY CLAIM OF PRODUCT LIABILITY, PERSONAL INJURY OR DEATH
ASSOCIATED
WITH ANY UNINTENDED USE, EVEN IF SUCH CLAIM ALLEGES THAT AN INTEL PARTY WAS
NEGLIGENT
REGARDING THE DESIGN OR MANUFACTURE OF THE LICENSED SOFTWARE.
      8.3 THE WARRANTY DISCLAIMER AND LIMITED LIABILITY ARE FUNDAMENTAL 
ELEMENTS
OF THE
BASIS OF THE BARGAIN BETWEEN INTEL AND LICENSEE. INTEL WOULD NOT BE ABLE TO
PROVIDE THE
SOFTWARE WITHOUT SUCH LIMITATIONS.
9. TERM AND TERMINATION.
      9.1 This Agreement. Intel may terminate this Agreement at any time if
Licensee violates its terms. Upon
termination, Licensee will immediately destroy the Licensed Software (including
providing certification of such
destruction back to Intel) or return all copies of the Licensed Software to
Intel. In the event of termination of this
Agreement, all licenses granted to Licensee hereunder (including all 
sublicenses
granted by Licensee pursuant to this
Agreement) shall immediately terminate.
10. MISCELLANEOUS
      10.1 Applicable Laws. Any claims arising under or relating to this
Agreement shall be governed by the internal
substantive laws of the State of Delaware or federal courts located in 
Delaware,
without regard to principles of conflict
of laws. Each party hereby agrees to jurisdiction and venue in the courts of 
the
State of Delaware for all disputes and
litigation arising under or relating to this Agreement. The parties agree that
the United Nations Convention on
Contracts for the International Sale of Goods is specifically excluded from
application to this Agreement. The parties
consent to the personal jurisdiction of the above courts. In connection with
your use of the Licensed Software, You
agree that You will comply with all applicable laws and regulations.
      10.2 Export Regulations / Export Control. You shall not export, either
directly or indirectly, any product,
service or technical data or system incorporating such items without first
obtaining any required license or other
approval from the U. S. Department of Commerce or any other agency or 
department
of the United States
Government. In the event any product is exported from the United States or
re-exported from a foreign destination by
You, You shall ensure that the distribution and export/re-export or import of
the product is in compliance with all laws,
regulations, orders, or other restrictions of the U.S. Export Administration
Regulations and the appropriate foreign
government. You agree that neither you nor any of your subsidiaries will
export/re-export any technical data, process,
product, or service, directly or indirectly, to any country for which the 
United
States government or any agency thereof
or the foreign government from where it is shipping requires an export license,
or other governmental approval,
without first obtaining such license or approval.
      10.3 Government Rights. The Licensed Software is a "commercial item" as
that term is defined in 48 C.F.R.
2.101, consisting of "commercial computer software" and "commercial computer
software documentation" as such
terms are used in 48 C.F.R. 12.212. Consistent with 48 C.F.R. 12.212 and 48
C.F.R 227.7202-1 through 227.7202-4,
You will provide the Software to the U.S. Government as an End User only
pursuant to the terms and conditions
therein.
10/09
      10.4 Assignment.        Intel may assign its rights or delegate its
obligations, or any part thereof under this
Agreement without prior consent from Licensee. Licensee may not assign, whether
in conjunction with a change of
ownership, merger, acquisition, sale or transfer of all, substantially all or
any part of Licensee's business or assets or
otherwise, either voluntarily, by operation of law of otherwise, any portion of
this Agreement. Any attempt by
Licensee to assign or delegate any rights, duties or obligations set forth in
this Agreement without Intel's prior written
consent shall be deemed a material breach of this Agreement and shall be null
and void. Except as provided above,
the terms and conditions of this Agreement shall bind and inure to each party's
successors and assigns.
      10.5 Entire Agreement. The terms and conditions of this Agreement
constitutes the entire agreement between
the parties with respect to the subject matter hereof, and merges and 
supersedes
all prior, contemporaneous
agreements, understandings, negotiations and discussions. Neither of the 
parties
hereto shall be bound by any
conditions, definitions, warranties, understandings or representations with
respect to the subject matter hereof other
than as expressly provided for herein. Intel is not obligated under any other
agreements unless they are in writing
and signed by an authorized representative of Intel. Without limiting the
foregoing, terms and conditions on any
purchase orders or similar materials submitted by You to Intel, and any terms
contained in Intel's standard
acknowledgment form that are in conflict with these terms, shall be of no force
or effect.
      10.6 Attorneys' Fees. In the event any proceeding or lawsuit is brought 
by
Intel or You in connection with this
Agreement, the prevailing party in such proceeding shall be entitled to receive
its costs, expert witness fees and
reasonable attorneys' fees, including costs and fees on appeal.
      10.7 Injunctive Relief. It is understood and agreed that, notwithstanding
any other provisions of this Agreement,
breach of Sections 2, 4 or 5 of this Agreement by You will cause Intel
irreparable damage for which recovery of
money damages would be inadequate, and that Intel shall be entitled to obtain
timely injunctive relief to protect its
rights under this Agreement in addition to any and all remedies available at 
law.
      10.8 Notices. All notices permitted or required under this Agreement 
shall
be in writing and shall be delivered by
personal delivery or by certified or registered mail, return receipt requested,
and shall be deemed given upon receipt
or five (5) days after deposit in the mail, whichever is sooner.
      10.9 No Agency. Nothing contained herein shall be construed as creating
any agency, employment relationship,
partnership, principal-agent or other form of joint enterprise between the 
parties.
      10.10 Severability. In the event that any provision of this Agreement
shall be unenforceable or invalid under
any applicable law or be so held by applicable court decision, such
unenforceability or invalidity shall not render this
Agreement unenforceable or invalid as a whole, and, in such event, such
provision shall be changed and interpreted
so as to best accomplish the objectives of such unenforceable or invalid
provision within the limits of applicable law or
applicable court decisions.
      10.11 Waiver. The failure of either party to require performance by the
other party of any provision hereof shall
not affect the full right to require such performance at any time thereafter;
nor shall the waiver by either party of a
breach of any provision hereof be taken or held to be a waiver of the provision
itself.
      10.12 Language. This Agreement is in the English language only, which
language shall be controlling in all
respects, and all versions of this Agreement in any other language shall be for
accommodation only and shall not be
binding on you or Intel. All communications and notices made or given pursuant
to this Agreement, and all
documentation and support to be provided, unless otherwise noted, shall be in
the English language.
10/09
                                         Exhibit A
                            Minimum End User License Terms
An End User may:
1. Copy the Licensed Software solely for backup or archival purposes, provided
that the license
   terms may (where appropriate) authorize the reproduction of a specified or
reasonable
   number of copies to be made and/or used by the End User.
2. Transfer the Licensed Software to another party if the receiving party 
agrees
to the terms
   substantially similar to and as protective as the terms set forth in this
Exhibit A, and
   Transferor retains no copies of the Licensed Software. Transfer of the
Licensed Software
   terminates the transferor's rights to use the Licensed Software.
An End User may not:
1. Sublicense or further distribute the Licensed Software (except as allowed in
the above section,
   or by the express terms of the license with respect to open source 
components);
2. Reverse engineer, decompile, or disassemble the Licensed Software (except
with respect to
   open source components);
3. Copy or modify the Licensed Software, in whole or in part (except as allowed
in the above
   section, or by the express terms of the license with respect to open source
components).
4. Remove any copyright notices from the Licensed Software.
An End User shall also be made aware that:
1. Title to the Licensed Software and all copies thereof remain with Licensee 
or
its suppliers, as
   applicable.
2. The Licensed Software is provided "AS IS" and that the warranties of
Merchantability, Fitness
   for a Particular Purpose and of Non-Infringement are expressly excluded.
3. The end user license may be terminated at any time if the End User is in
breach of any of its
   terms and conditions. Upon termination, the End User must immediately cease
use of the
   Licensed Software and destroy the Licensed Software.
4. All indirect, special, incidental and consequential damages of any kind are
disclaimed to the
   extent allowed by law.
5. The Licensed Software is provided with RESTRICTED RIGHTS, as that term is
understood in
   government procurement.
6. The End User agrees to comply with all applicable export control laws with
respect to the
   Licensed Software.
%% The following software may be included in this product:  gdl header file. Use of any of this software is governed by the terms of the license below: 

The attached license is in reference to the files listed below from the Intel® 
Media Processor CE3100 SDK:

For JMF Plugin:

    display_config.c
    display_config.h
    gst_pipe.c
    gst_pipe.h
    GstPlayer.cpp
    psi_handling.c
    psi_handling.h
    psi_handling_wrapper.c
    psi_handling_wrapper.h
    read_configfile.c
    read_configfile.h
    README_sg_player.txt
    sg_player
    sg_player.c
    sg_player.h

and

For JAVAFX JOGL Component:

    gdl.h

Intel hereby grants Oracle America the right to distribute the Intel code 
described above under the terms of the Intel Media Processor Software 
Development Kit (SDK) license agreement.  Notwithstanding the terms of  Intel 
Media Processor Software Development Kit (SDK) license agreement, Intel hereby 
grants Oracle America the right to publicly display, demonstrate, and 
distribute the Object Code of Derived Code and Licensed Software for Oracle's 
support of Intel-based consumer electronics platforms. Intel further grants to 
Orace the right to disclose the Object Code of Derived Code to third parties who 
participate in the testing, evaluation and demonstration of the Oracle JMF and 
JOGL technology in connection with Oracle's support of Intel-based consumer 
electronics platforms.  Intel acknowledges and agrees that the Licensed 
Software may be hosted for testing, evaluation, and demonstration purposes at 
a third party's location, and that Oracle has the right to provide the Licensed 
Software to these third parties.  For purposes of this permission, the 
Licensed Software is Distributable as Sample Source Code and may be 
distributed in Object Code form (except for gdl.h, which may be delivered in 
the form customary provided for header files).

Please acknowledge acceptance of these terms in a return email.

 

Regards,

 

Jeffery Noah

Digital Home Group, CE Platform AE
Intel Corp.
+1 503 712 8384 begin_of_the_skype_highlighting              +1 503 712 8384      end_of_the_skype_highlighting
+1 503 799 7045 begin_of_the_skype_highlighting              +1 503 799 7045      end_of_the_skype_highlighting cell

                 INTEL SOFTWARE LICENSE AGREEMENT
                      Intel Media Processor Software Development Kit (SDK)
              (Internal Use, Object Code Distribution and Sample Source Code
Distribution)
              IMPORTANT - READ BEFORE COPYING, INSTALLING OR USING.
DO NOT USE OR LOAD THIS INTEL SOFTWARE UNTIL YOU HAVE CAREFULLY
READ THE FOLLOWING TERMS AND CONDITIONS. BY LOADING OR USING
THIS INTEL SOFTWARE, YOU ("YOU" OR "LICENSEE") AGREE TO THE TERMS
OF THIS INTEL SOFTWARE LICENSE AGREEMENT (THIS "AGREEMENT"). IF
YOU DO NOT WISH TO SO AGREE, DO NOT COPY, INSTALL OR USE THIS INTEL
SOFTWARE. IF YOU ARE AN AGENT OR EMPLOYEE OF A LEGAL ENTITY YOU
REPRESENT AND WARRANT THAT YOU HAVE THE AUTHORITY TO BIND SUCH
LEGAL ENTITY TO THIS AGREEMENT.
Portions of the Intel Software within this SDK may be Pre-Production Licensed
Software as defined below. The Pre-Production Licensed Software sections of
this SDK may not have been fully validated by Intel or be feature-complete. 
Intel
may make updates available to You of production versions which may have gone
through Intel's software validation and quality control efforts. YOU ARE
RESPONSIBLE FOR YOUR DECISION TO USE PRE-PRODUCTION LICENSED
SOFTWARE IN YOUR COMMERCIAL PRODUCT.
1. DEFINED TERMS
     1.1 "Confidential Information" means the Licensed Software provided in
Source Code form, and the Licensed
Documentation that is not End User Documentation.
     1.2 "Derived Code" means derivative work, as defined by 17 USC Section 
101,
of the Licensed Software
provided by Intel to Licensee in Source Code form.
     1.3 "Distributor" means a third-party, including any of Licensee's
subsidiaries or affiliates, that is authorized by
Licensee to distribute, Sell, or Offer Products for Sale.
     1.4 "End User" means the final or ultimate user of a Product that uses a
Product for its own purposes and not for
further distribution or resale.
     1.5 "End User Documentation" means Licensed Documentation that may be
included with the Licensed
Software.
     1.6 "Independent Software Vendor" or "ISV" means an individual or entity
that makes or produces software that
incorporates Licensed Software.
     1.7 "Intel" means Intel Corporation and its wholly owned subsidiaries and
affiliates as the case may be.
     1.8 "Intel Party" or "Intel Parties" means Intel and its officers,
directors, employees, affiliates, sub-contractors,
subsidiaries and suppliers (and any respective officer, director, employee,
affiliate, sub-contractor or subsidiary
thereof).
     1.9 "Internal Use Software" means the Licensed Software identified as 
Intel
Confidential, or other confidential
markings in the File Header.
     1.10 "Licensed Documentation" means installation and user guides, and
manuals and other documentation in
either printed or machine-viewable form documentation corresponding to the
Licensed Software provided by Intel in
connection with, under, or subject to this Agreement.
10/09
      1.11 "Licensed Patent Claims" means those claims of Intel's patents that
(a) are infringed by the Licensed
Software (as delivered by Intel in its unmodified form) and (b) Intel has the
right to license.
      1.12 "Licensed Software" means any computing programming code, including
Pre-Production Licensed
Software, that is provided in connection with, under, or subject to this
Agreement, but does not include computing
programming code that is (i) delivered with the Licensed Software that is Open
Source Software and/or (ii) subject to
an agreement, obligation and/or license (in each case whether or not
accompanying such computing programming
code) intended to supersede or override this Agreement. The file directories,
sub-directories and individual File
Headers included with the Licensed Software shall contain information to
identify the Licensed Software and any
Open Source Software.
      1.13 "Licensee" or "Licensee's" means Licensee as an individual or, if
Licensee are accepting these terms on
behalf of a legal entity (such as a corporation, partnership or government
agency), "Licensee", "Licensee's", "You" or
"Your" means such legal entity.
      1.14 "File Header(s)" means the information provided with the Licensed
Software and any Open Source
Software that identifies (i) Internal Use Software, Distributable As Object
Software, Distributable As Sample Source
Software, or Open Source Software, and (ii) any notices or additional
information about the Licensed Software or
Open Source Software.
      1.15 "Object Code" means computer programming code in binary form 
suitable
for machine execution by a
processor without the intervening steps of interpretation or compilation.
      1.16 "Open Source Software" means any software that requires as a
condition of use, modification and/or
distribution of such software that such software or other software incorporated
into, derived from or distributed with
such software (a) be disclosed or distributed in Source Code form; (b) be
licensed by the user to third parties for the
purpose of making and/or distributing derivative works; or (c) be
redistributable at no charge. Open Source Software
includes, without limitation, software licensed or distributed under any of the
following licenses or distribution models,
or licenses or distribution models substantially similar to any of the
following: (a) GNU's General Public License (GPL)
or Lesser/Library GPL (LGPL), (b) the Artistic License (e.g., PERL), (c) the
Mozilla Public License, (d) the Netscape
Public License, (e) the Oracle Community Source License (SCSL), (f) the Oracle
Industry Source License (SISL), and (g)
the Common Public License (CPL).
      1.17 "Original Equipment Manufacturer" or "OEM" means an individual or
entity that makes or produce
hardware products that incorporates Licensed Software.
      1.18 "Pre-Production Licensed Software" means the pre-production versions
or features of Licensed Software
that have not been validated nor passed Intel's software quality controls. The
release notes included or provided with
the PDK shall contain information to help identify the Pre-Production Licensed
Software.
      1.19 "Product" means a consumer electronics hardware product designed and
manufactured by You, or solely
for You, to Your specifications and sold under Your brand or trade name that
includes the Intel® Media Processor CE
3100 or Intel® AtomTM processor CE4100.
      1.20 "Distributable As Object Software" means the Licensed Software
provided in Object Code form that is not
identified as Internal Use Software.
      1.21 "Distributable As Sample Source Software" means the Licensed 
Software
in Source Code form that is
as identified as "Distributable As Sample Source Software" in the File Header
included with the Licensed Software.
      1.22 "Sell" and "Offer for Sale" mean, respectively, for the purposes of
the patent licenses granted under
Section 2, to sublicense, or offer to sublicense, the Licensed Software, as 
part
of a sale, or offer to sell, the Licensee
Products, and "Sale" and "Offer to Sell", in no way mean to transfer ownership
rights in the Licensed Software.
      1.23 "Source Code" means computer programming code in an uncompiled form
readable by humans which
cannot be executed by a processor unless it is complied into Object Code.
2. LICENSE GRANTS AND RESTRICTIONS
      2.1 Licenses to Internal Use Software. Intel grants to Licensee the
following non-transferable, non-exclusive,
worldwide, royalty-free, perpetual (unless terminated in accordance with the
Section 9 ("Termination") below), non-
sublicensable licenses:
           2.1.1 Internal Use License. A license (i) under Intel's copyrights 
to
reproduce and (ii) under Intel's Licensed
Patent Claims to use, a reasonable number of copies the Internal Use Software,
alone or to the extent the Internal
Use Software is incorporated into Derived Code of the Internal Use Software,
internally within Licensee's facilities
solely and exclusively for the purpose of manufacture and/or internal
development of Products; and
10/09
           2.1.2 License to Create Derivative Works. A license under Intel's
copyrights to create Derived Code of the
Internal Use Software provided in Sample Source Code form, internally within
Licensee's facilities and solely and
exclusively for the purpose of manufacture and/or internal development of 
Products.
      2.2 Licenses to Distributable As Object Software. Intel grants to 
Licensee
the following non-transferable, non-
exclusive, worldwide, royalty-free, perpetual (unless terminated in accordance
with the Section 9 ("Termination")
below), non-sublicensable (except pursuant to Section 2.2.4 below) licenses:
           2.2.1 Internal Use License. A license (i) under Intel's copyrights 
to
reproduce and (ii) under Intel's Licensed
Patent Claims to use, a reasonable number of copies the Distributable As Object
Software, alone or to the extent the
Distributable as Object Software is incorporated into Derived Code of the
Distributable As Object Software, internally
within Licensee's facilities solely and exclusively for the purpose of
manufacture and/or internal development of
Products;
           2.2.2 License to Create Derivative Works. A license under Intel's
copyrights to create Derived Code of the
Distributable As Object Software provided in Sample Source Code form, 
internally
within Licensee's facilities and
solely and exclusively for the purpose of manufacture and/or internal
development of Products; and
           2.2.3 Object Code Distribution License. A license (i) under Intel's
copyrights to distribute and (ii) under
Intel's Licensed Patent Claims to Sell, Offer for Sale, make, and import, the
Distributable As Object Software, in
Object Code form, alone or to the extent the Distributable as Object Software 
is
incorporated into Derived Code of
Distributable As Object Software, solely as part of and within Products.
           2.2.4 Object Code Distribution Sublicense.
                     2.2.4.1 OEMs. Intel grants to a Licensee acting as an OEM
the right to sublicense (subject to the
minimum end user license terms set forth in Exhibit A) to End Users through one
or more tiers of Distributors, the
right to execute the Distributable As Object Software, alone or to the extent
the Distributable as Object Software is
incorporated into Derived Code, solely within or as part of a Product.
                     2.2.4.2 ISVs. Intel grants to a Licensee acting as an ISV
(i) the right to sublicense (subject to the
minimum end user license terms set forth in Exhibit A) to End Users through one
or more tiers of Distributors, the
right to execute the Distributable As Object Software, alone or to the extent
the Distributable as Object Software is
incorporated into Derived Code, solely within or as part of a Product and (ii)
the right to sublicense to OEMs the
Object Code form distribution license set forth in Section 2.2.3 subject to the
restriction in Section 2.2.4.1.
      2.3 Licenses to Distributable As Sample Source Software. Intel grants to
Licensee the following non-
transferable, non-exclusive, worldwide, royalty-free, perpetual (unless
terminated in accordance with the Section 9
("Termination") below), non-sublicensable (except pursuant to Section 2.5,
below) licenses
           2.3.1 License to Create Derivative Works. A license under Intel's
copyrights to create Derived Code of the
Distributable As Sample Source Software provided in Source Code form, 
internally
within Licensee's facilities and
solely and exclusively for the purpose of manufacture and/or internal
development of Products;
           2.3.2 Internal Use License. A license (i) under Intel's copyrights 
to
reproduce and (ii) under Intel's Licensed
Patent Claims to use, a reasonable number of copies the Distributable As Sample
Source Software, alone or to the
extent the Distributable as Sample Source Software is incorporated into Derived
Code of the Distributable As Sample
Source Software, internally within Licensee's facilities solely and exclusively
for the purpose of manufacture and/or
internal development of Products;
           2.3.3 Object Code Distribution License. A license (i) under Intel's
copyrights to distribute and (ii) under
Intel's Licensed Patent Claims to Sell, Offer for Sale, make and import, the
Distributable As Sample Source Software,
in Object Code form, alone or to the extent the Distributable as Sample Source
Software is incorporated into Derived
Code of Distributable As Sample Source Software, solely as part of and within
Products; and
           2.3.4 Source Code Distribution License. A license (i) under Intel's
copyrights to distribute and (ii) under
Intel's Licensed Patent Claims to Sell, Offer for Sale, and import, the
Distributable As Sample Source Software, in
Source Code form, alone or to the extent the Distributable as Sample Source
Software is incorporated into Derived
Code of Distributable As Sample Source Software, to OEMs, solely as part of and
within Products.
           2.3.5 Object and Source Code Distribution Sublicense.
                     2.3.5.1 OEMs. Intel grants to a Licensee acting as an OEM
the right to sublicense (subject to the
minimum end user license terms set forth in Exhibit A) to End Users through one
or more tiers of Distributors, the
right to execute the Distributable As Sample Source Software in Object Code
form, alone or to the extent the
Distributable as Sample Source Software is incorporated into Derived Code,
solely within or as part of a Product.
10/09
                     2.3.5.2 ISVs. Intel grants to a Licensee acting as an ISV
(i) the right to sublicense (subject to the
minimum end user license terms set forth in Exhibit A) to End Users through one
or more tiers of Distributors, the
right to execute the Distributable As Source Software in Object Code form, 
alone
or to the extent the Distributable as
Sample Source Software is incorporated into Derived Code, solely within or as
part of a Product; (ii) the right to
sublicense to OEMs the Object Code form distribution license set forth in
section 2.3.3 subject to the restriction in
Section 2.3.5.1 and (iii) the right to sublicense to OEMs the license to create
Derived Code of the Distributable As
Source Software set forth in Section 2.3.4 subject to the restriction in 
Section
2.3.5.1.
      2.4 Licenses to Documentation. Intel grants to Licensee the following
non-transferable, non-exclusive,
worldwide, royalty-free, perpetual (unless terminated in accordance with the
Section 9 ("Termination") below), non-
sublicensable licenses
           2.4.1 Internal Use License. A license under Intel's copyrights to
reproduce a reasonable number of copies
of the Licensed Documentation internally within Licensee's facilities solely 
and
exclusively for the purpose of
manufacture and/or internal development of Products; and
           2.4.2 End User Documentation Distribution License. A license under
Intel's copyrights to distribute End
User Documentation to End Users through one or more tiers of Distributors 
solely
with Products.
      2.5 Restrictions. Neither Licensee, nor any OEM, or Distributor, shall:
           2.5.1 Have any right or license to reverse engineer, decompile or
disassemble any Licensed Software not
provided in Source Code form, except in jurisdictions where licenses for such
activities cannot be withheld;
           2.5.2 Grant to any third-party, or encourage any third-party to
exercise, any rights broader than those rights
Intel has granted to Licensee under this Agreement, and any such attempt is 
null
and void;
           2.5.3 Alter, remove or obscure any proprietary notices from the
Licensed Software relating to Intel's or its
vendors' intellectual property rights;
           2.5.4 Make any statement that any Products are endorsed by, 
certified
by or that its performance is
guaranteed by Intel or use Intel's name or trademarks to market any Product
without express prior written permission
from Intel;
           2.5.5 Imply or otherwise indicate that Licensee is selling or
transferring title to any Licensed Software; and
           2.5.6 Grant an End User any right to sublicense the Licensed 
Software
to any party.
      2.6 Written Agreements. All agreements or licenses between Licensee and
any third party with respect to the
Licensed Software must be in writing and at least as protective of Intel's
rights in the Licensed Software as this
Agreement. For Licensee's convenience, any written agreement referenced in this
Section 2.6 with an OEM,
Distributor or End User may also pertain to other computer programming code
without specifically referencing the
Licensed Software (as long as it covers the Licensed Software), but Intel 
grants
no right hereunder with respect to
such other computer programming code.
      2.7 Open Source Software. In the event Open Source Software is included
with Licensed Software, such Open
Source Software is licensed pursuant to the applicable Open Source Software
license agreement identified in the
Open Source Software comments in the applicable source code file(s) and/or File
Header provided with Licensed
Software. Additional detail may be provided (where applicable) in the
accompanying on-line documentation. With
respect to the Open Source Software, nothing in this Agreement limits any 
rights
under, or grants rights that
supersede, the terms of any applicable Open Source Software license agreement.
Neither Licensee nor any OEM or
Distributor, shall subject the Licensed Software or Licensed Documentation, in
whole or in part, to any license
obligations associated with Open Source Software including combining or
distributing the Licensed Software and/or
Documentation with Open Source Software in a manner that subjects Intel or any
portion of the Licensed Software
provided by Intel hereunder to any license obligations of such Open Source 
Software.
      2.8 Additional Terms For Windows Media Enabled Versions. This product
includes technology owned by
Microsoft Corporation and cannot be used or further distributed by Licensee
without a license from Microsoft or a
Microsoft Affiliate. If you have questions on whether you received these
components, please contact your Intel
representative.
      2.9 Media Player Sample Source Code. The Licensed Software may contain
sample application source code
for the XPSM media player. You can use the Licensed Software and any 
application
sample source code provided
solely in connection with creating media playback functionality in your 
Product,
but you shall not use the application
sample source code, or any other portions of the Licensed Software, to design,
produce, sell or otherwise transfer or
distribute Products or software, where such Products or software are designed 
to
circumvent the requirements or
effectiveness of any content protection or digital rights management
technologies (or assist others directly or
indirectly to do any of the foregoing).
10/09
3. License to Use Comments and Suggestions. This Agreement does NOT obligate
Licensee to provide Intel with
comments or suggestions regarding the Licensed Software. However, should
Licensee provide Intel with comments
or suggestions for the modification, correction, improvement or enhancement of
(a) the Licensed Software or (b) Intel
products or processes which work with the Licensed Software, Licensee grants to
Intel a non-exclusive, irrevocable,
worldwide, royalty-free license, with the right to sublicense Intel's licensees
and customers, under Licensee's
intellectual property rights, the rights to use and disclose such comments and
suggestions in any manner Intel
chooses and to display, perform, copy, make, have made, use, sell, and 
otherwise
dispose of Intel's and its
sublicensee's products embodying such comments and suggestions in any manner 
and
via any media Intel chooses,
without reference to the source.
4. OWNERSHIP, NO OTHER RIGHTS.
      4.1 The Licensed Software is protected by the intellectual property laws
of the United States and other countries,
and international treaty provisions. Licensee and Intel agree that except as
otherwise expressly provided in Section 2
("License Grants") above, no license or other right is granted by Intel to
Licensee by implication, estoppel or
otherwise, under Intel patents, copyrights, trademarks, or other intellectual
property rights. All rights not expressly
granted to Licensee pursuant to Section 2 of this Agreement are hereby 
expressly
reserved for Intel and/or the Intel
Parties and are expressly excluded from this Agreement.
      4.2 Title to all copies of the Licensed Software and any intellectual
property therein remains with Intel and/or the
Intel Parties. Licensee may not disclose the terms of this Agreement or use
Intel's name in any publications,
advertisements, or other announcements without Intel's prior written consent.
This Agreement does not grant
Licensee any rights to use Intel's trademarks.
      4.3 Licensee acknowledges and agrees that Licensee's use of the Licensed
Software or distribution thereof with
Products as permitted by this Agreement may require Licensee to procure
license(s) from one or more third parties
that may hold intellectual property rights applicable to the media format,
decoding, encoding, transcoding, digital
rights management capabilities and/or content protection technologies of the
Licensed Software, if any.
5. CONFIDENTIALITY
      5.1 Confidentiality. Confidential Information disclosed under this
Agreement is governed by Licensee's
Corporate Non Disclosure Agreement ("CNDA") with Intel (if any). If Licensee 
and
Intel do not have a CNDA, then the
provisions of Section 5.2 shall apply to Confidential Information disclosed
under this Agreement.
      5.2 Obligations of Receiving Party. Licensee will maintain the
confidentiality of the Confidential Information of
Intel with at least the same degree of care that it uses to protect its own
confidential and proprietary information, but
no less than a reasonable degree of care under the circumstances. Licensee will
not disclose any Confidential
Information to any employees or to any third parties except to the Licensee's
employees, parent company and
majority-owned subsidiaries, and, in the case of Distributable Sample Source
Software, OEMs who have a need to
know and who agree to abide by nondisclosure terms at least as comprehensive as
those set forth herein; provided
that the Licensee will be liable for breach by any such entity. For the 
purposes
of this Agreement, the term
"employees" shall include independent contractors of Licensee, who have agreed
in writing to Licensee that they are
subject to the terms of this Agreement, and they retain no license rights or
other rights hereunder once they have
discontinued their performance of work on Licensee's behalf. Licensee, however,
will not be liable for the disclosure
of any Confidential Information which is:
      a.   rightfully in the public domain other than by a breach of a duty to
Intel;
      b.   rightfully received from a third party without any obligation of
confidentiality;
      c.   rightfully known to the Licensee without any limitation on use or
disclosure prior to its receipt from Intel;
      d.   independently developed by employees of Licensee; or
      e.   generally made available to third parties by Intel without
restriction on disclosure.
Title or the right to possess Confidential Information as between the parties
will remain with Intel.
6. EXCLUSION OF WARRANTIES. THE LICENSED SOFTWARE IS PROVIDED "AS-IS", WITHOUT
WARRANTY
OF ANY KIND AND POSSIBLY WITH DEFECTS. INTEL AND THE INTEL PARTIES MAKE NO
WARRANTIES,
EITHER EXPRESS OR IMPLIED, WITH RESPECT TO THE LICENSED SOFTWARE. INTEL AND THE
INTEL
PARTIES SPECIFICALLY DISCLAIM THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
FITNESS FOR A
PARTICULAR PURPOSE AND ANY WARRANTY AGAINST INFRINGEMENT OF ANY INTELLECTUAL
PROPERTY RIGHT OF ANY THIRD PARTY.
7. SUPPORT. Intel may make changes to the Licensed Software, or to items
referenced therein, at any time without
notice, but is not obligated to support, update or provide training for the
Licensed Software. Intel may in its sole
10/09
discretion offer such services under separate terms at Intel's then-current
rates. Licensee may request additional
information on Intel's service offerings from an Intel sales representative.
Licensee agrees to be solely responsible to
Licensee's OEMs, Distributors and End Users for any update or support 
obligation
or other liability which may arise
from the distribution of a Product.
8. LIMITATION OF LIABILITY.
      8.1 IN NO EVENT SHALL INTEL OR THE INTEL PARTIES BE LIABLE FOR ANY 
DAMAGES
WHATSOEVER
(INCLUDING, WITHOUT LIMITATION, LOST PROFITS, LOST DATA, LOSS OF GOODWILL, 
BUSINESS
INTERRUPTION, OR LOST INFORMATION) ARISING OUT OF THE USE OF OR INABILITY TO 
USE THE
LICENSED SOFTWARE, EVEN IF AN INTEL PARTY HAS BEEN ADVISED OF THE POSSIBILITY 
OF
SUCH
DAMAGES. SOME JURISDICTIONS PROHIBIT EXCLUSION OR LIMITATION OF LIABILITY FOR
IMPLIED
WARRANTIES OR CONSEQUENTIAL OR INCIDENTAL DAMAGES, SO THE ABOVE LIMITATION MAY 
NOT
APPLY TO LICENSEE. LICENSEE MAY ALSO HAVE OTHER LEGAL RIGHTS THAT VARY FROM
JURISDICTION
TO JURISDICTION.
      8.2 THE LICENSED SOFTWARE LICENSED HEREUNDER IS NOT DESIGNED OR INTENDED
FOR USE IN
ANY MEDICAL, LIFE SAVING OR LIFE SUSTAINING SYSTEMS, TRANSPORTATION SYSTEMS, 
NUCLEAR
SYSTEMS, OR FOR ANY OTHER MISSION CRITICAL APPLICATION IN WHICH THE FAILURE OF 
THE
LICENSED SOFTWARE COULD LEAD TO PERSONAL INJURY OR DEATH. LICENSEE SHALL
INDEMNIFY AND
HOLD INTEL AND THE INTEL PARTIES HARMLESS AGAINST ALL CLAIMS, COSTS, DAMAGES, 
AND
EXPENSES, AND REASONABLE ATTORNEY FEES ARISING OUT OF, DIRECTLY OR INDIRECTLY,
THE USE
AND DISTRIBUTION OF THE LICENSED SOFTWARE BY LICENSEE, OEMs, DISTRIBUTORS, END
USERS, OR
ANY THIRD PARTY, AND ANY CLAIM OF PRODUCT LIABILITY, PERSONAL INJURY OR DEATH
ASSOCIATED
WITH ANY UNINTENDED USE, EVEN IF SUCH CLAIM ALLEGES THAT AN INTEL PARTY WAS
NEGLIGENT
REGARDING THE DESIGN OR MANUFACTURE OF THE LICENSED SOFTWARE.
      8.3 THE WARRANTY DISCLAIMER AND LIMITED LIABILITY ARE FUNDAMENTAL 
ELEMENTS
OF THE
BASIS OF THE BARGAIN BETWEEN INTEL AND LICENSEE. INTEL WOULD NOT BE ABLE TO
PROVIDE THE
SOFTWARE WITHOUT SUCH LIMITATIONS.
9. TERM AND TERMINATION.
      9.1 This Agreement. Intel may terminate this Agreement at any time if
Licensee violates its terms. Upon
termination, Licensee will immediately destroy the Licensed Software (including
providing certification of such
destruction back to Intel) or return all copies of the Licensed Software to
Intel. In the event of termination of this
Agreement, all licenses granted to Licensee hereunder (including all 
sublicenses
granted by Licensee pursuant to this
Agreement) shall immediately terminate.
10. MISCELLANEOUS
      10.1 Applicable Laws. Any claims arising under or relating to this
Agreement shall be governed by the internal
substantive laws of the State of Delaware or federal courts located in 
Delaware,
without regard to principles of conflict
of laws. Each party hereby agrees to jurisdiction and venue in the courts of 
the
State of Delaware for all disputes and
litigation arising under or relating to this Agreement. The parties agree that
the United Nations Convention on
Contracts for the International Sale of Goods is specifically excluded from
application to this Agreement. The parties
consent to the personal jurisdiction of the above courts. In connection with
your use of the Licensed Software, You
agree that You will comply with all applicable laws and regulations.
      10.2 Export Regulations / Export Control. You shall not export, either
directly or indirectly, any product,
service or technical data or system incorporating such items without first
obtaining any required license or other
approval from the U. S. Department of Commerce or any other agency or 
department
of the United States
Government. In the event any product is exported from the United States or
re-exported from a foreign destination by
You, You shall ensure that the distribution and export/re-export or import of
the product is in compliance with all laws,
regulations, orders, or other restrictions of the U.S. Export Administration
Regulations and the appropriate foreign
government. You agree that neither you nor any of your subsidiaries will
export/re-export any technical data, process,
product, or service, directly or indirectly, to any country for which the 
United
States government or any agency thereof
or the foreign government from where it is shipping requires an export license,
or other governmental approval,
without first obtaining such license or approval.
      10.3 Government Rights. The Licensed Software is a "commercial item" as
that term is defined in 48 C.F.R.
2.101, consisting of "commercial computer software" and "commercial computer
software documentation" as such
terms are used in 48 C.F.R. 12.212. Consistent with 48 C.F.R. 12.212 and 48
C.F.R 227.7202-1 through 227.7202-4,
You will provide the Software to the U.S. Government as an End User only
pursuant to the terms and conditions
therein.
10/09
      10.4 Assignment.        Intel may assign its rights or delegate its
obligations, or any part thereof under this
Agreement without prior consent from Licensee. Licensee may not assign, whether
in conjunction with a change of
ownership, merger, acquisition, sale or transfer of all, substantially all or
any part of Licensee's business or assets or
otherwise, either voluntarily, by operation of law of otherwise, any portion of
this Agreement. Any attempt by
Licensee to assign or delegate any rights, duties or obligations set forth in
this Agreement without Intel's prior written
consent shall be deemed a material breach of this Agreement and shall be null
and void. Except as provided above,
the terms and conditions of this Agreement shall bind and inure to each party's
successors and assigns.
      10.5 Entire Agreement. The terms and conditions of this Agreement
constitutes the entire agreement between
the parties with respect to the subject matter hereof, and merges and 
supersedes
all prior, contemporaneous
agreements, understandings, negotiations and discussions. Neither of the 
parties
hereto shall be bound by any
conditions, definitions, warranties, understandings or representations with
respect to the subject matter hereof other
than as expressly provided for herein. Intel is not obligated under any other
agreements unless they are in writing
and signed by an authorized representative of Intel. Without limiting the
foregoing, terms and conditions on any
purchase orders or similar materials submitted by You to Intel, and any terms
contained in Intel's standard
acknowledgment form that are in conflict with these terms, shall be of no force
or effect.
      10.6 Attorneys' Fees. In the event any proceeding or lawsuit is brought 
by
Intel or You in connection with this
Agreement, the prevailing party in such proceeding shall be entitled to receive
its costs, expert witness fees and
reasonable attorneys' fees, including costs and fees on appeal.
      10.7 Injunctive Relief. It is understood and agreed that, notwithstanding
any other provisions of this Agreement,
breach of Sections 2, 4 or 5 of this Agreement by You will cause Intel
irreparable damage for which recovery of
money damages would be inadequate, and that Intel shall be entitled to obtain
timely injunctive relief to protect its
rights under this Agreement in addition to any and all remedies available at 
law.
      10.8 Notices. All notices permitted or required under this Agreement 
shall
be in writing and shall be delivered by
personal delivery or by certified or registered mail, return receipt requested,
and shall be deemed given upon receipt
or five (5) days after deposit in the mail, whichever is sooner.
      10.9 No Agency. Nothing contained herein shall be construed as creating
any agency, employment relationship,
partnership, principal-agent or other form of joint enterprise between the 
parties.
      10.10 Severability. In the event that any provision of this Agreement
shall be unenforceable or invalid under
any applicable law or be so held by applicable court decision, such
unenforceability or invalidity shall not render this
Agreement unenforceable or invalid as a whole, and, in such event, such
provision shall be changed and interpreted
so as to best accomplish the objectives of such unenforceable or invalid
provision within the limits of applicable law or
applicable court decisions.
      10.11 Waiver. The failure of either party to require performance by the
other party of any provision hereof shall
not affect the full right to require such performance at any time thereafter;
nor shall the waiver by either party of a
breach of any provision hereof be taken or held to be a waiver of the provision
itself.
      10.12 Language. This Agreement is in the English language only, which
language shall be controlling in all
respects, and all versions of this Agreement in any other language shall be for
accommodation only and shall not be
binding on you or Intel. All communications and notices made or given pursuant
to this Agreement, and all
documentation and support to be provided, unless otherwise noted, shall be in
the English language.
10/09
                                         Exhibit A
                            Minimum End User License Terms
An End User may:
1. Copy the Licensed Software solely for backup or archival purposes, provided
that the license
   terms may (where appropriate) authorize the reproduction of a specified or
reasonable
   number of copies to be made and/or used by the End User.
2. Transfer the Licensed Software to another party if the receiving party 
agrees
to the terms
   substantially similar to and as protective as the terms set forth in this
Exhibit A, and
   Transferor retains no copies of the Licensed Software. Transfer of the
Licensed Software
   terminates the transferor's rights to use the Licensed Software.
An End User may not:
1. Sublicense or further distribute the Licensed Software (except as allowed in
the above section,
   or by the express terms of the license with respect to open source 
components);
2. Reverse engineer, decompile, or disassemble the Licensed Software (except
with respect to
   open source components);
3. Copy or modify the Licensed Software, in whole or in part (except as allowed
in the above
   section, or by the express terms of the license with respect to open source
components).
4. Remove any copyright notices from the Licensed Software.
An End User shall also be made aware that:
1. Title to the Licensed Software and all copies thereof remain with Licensee 
or
its suppliers, as
   applicable.
2. The Licensed Software is provided "AS IS" and that the warranties of
Merchantability, Fitness
   for a Particular Purpose and of Non-Infringement are expressly excluded.
3. The end user license may be terminated at any time if the End User is in
breach of any of its
   terms and conditions. Upon termination, the End User must immediately cease
use of the
   Licensed Software and destroy the Licensed Software.
4. All indirect, special, incidental and consequential damages of any kind are
disclaimed to the
   extent allowed by law.
5. The Licensed Software is provided with RESTRICTED RIGHTS, as that term is
understood in
   government procurement.
6. The End User agrees to comply with all applicable export control laws with
respect to the
   Licensed Software.



